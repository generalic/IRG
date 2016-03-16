package hr.fer.zemris.irg.vjezba1.task4;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import hr.fer.zemris.irg.vjezba1.task4.bresenham.BresenhamLDA;
import hr.fer.zemris.irg.vjezba1.task4.model.CustomModel;
import hr.fer.zemris.irg.vjezba1.task4.model.DrawingModel;
import hr.fer.zemris.irg.vjezba1.task4.model.DrawingModelListener;

public class LineDrawing2 extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int Y_OFFSET = 20;

	static {
		GLProfile.initSingleton();
	}

	private DrawingModel model = new CustomModel();
	private GLCanvas canvas = new CustomCanvas(model);

	private Point start;
	private Point end;

	private Runnable glLine;

	private boolean firstClick;

	public LineDrawing2() {
		initGUI();
		try {
			askForPoints();
		} catch (Exception e) {
			System.err.println("Invalid coordinates.");
			System.exit(1);
		}
	}

	private void askForPoints() {
		start = getPoint("Start");
		end = getPoint("End");

		glLine = () -> {
			GL2 gl2 = canvas.getGL().getGL2();
			gl2.glBegin(GL.GL_LINES);
			gl2.glVertex2i(start.x, start.y + Y_OFFSET);
			gl2.glVertex2i(end.x, end.y + Y_OFFSET);
			gl2.glEnd();
		};
		model.add(new Line(start, end));
	}

	private Point getPoint(String pointName) {
		String point = JOptionPane.showInputDialog(
				LineDrawing2.this,
				"(x,y):",
				pointName + " point",
				JOptionPane.QUESTION_MESSAGE
		);
		String[] split = point.split(",");
		return new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
	}

	private void initGUI() {
		setTitle("Primjer prikaza obojanog trokuta");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		getContentPane().add(canvas, BorderLayout.CENTER);
		setSize(1200, 600);
		setVisible(true);
		canvas.requestFocusInWindow();
	}

	@SuppressWarnings("serial")
	private class CustomCanvas extends GLCanvas implements DrawingModelListener {

		private DrawingModel model;

		public CustomCanvas(DrawingModel model) {
			this.model = model;
			model.addDrawingModelListener(this);
//			// Reagiranje na pritiske tipki na misu...
//			addMouseListener(mouseClick);
//			// Reagiranje na pomicanje pokazivaca misa...
//			addMouseMotionListener(mouseMotion);
//			// Reagiranje na pritiske tipaka na tipkovnici...
//			addKeyListener(keyClick);
			// Reagiranje na promjenu velicine platna,na zahtjev za
			// crtanjem i slicno...
			addGLEventListener(glEvent);
		}

		private MouseListener mouseClick = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3) {
					firstClick = false;
					repaint();
					return;
				}
				if(!firstClick) {
					firstClick = true;
					start = e.getPoint();
				} else {
					firstClick = false;
					addLine();
				}
//				System.out.println("Mis je kliknut na: x = " + e.getX() + ", y = " + e.getY());
				// Napravi nesto
				// ...
				// Posalji zahtjev za ponovnim crtanjem...
//				display();
			}
		};

		private MouseMotionListener mouseMotion = new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

//				System.out.println("Mis pomaknut na: x = " + e.getX() + ", y = " + e.getY());
				end = e.getPoint();
				if(firstClick) {
					repaint();
				}
				// Napravi nesto
				// ...
				// Posalji zahtjev za ponovnim crtanjem...
//				display();
			}
		};

		private KeyListener keyClick = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_R) {
					e.consume();
					// Napravinesto
					// ...
					// Posaljizahtjevzaponovnimcrtanjem...
					display();
				}
			}
		};


		private GLEventListener glEvent = new GLEventListener() {
			@Override
			public void reshape(GLAutoDrawable glautodrawable, int x, int y, int width, int height) {

				GL2 gl2 = glautodrawable.getGL().getGL2();
				gl2.glViewport(0, 0, width, height);

				gl2.glMatrixMode(GL2.GL_PROJECTION);
				gl2.glLoadIdentity();
				// coordinate system original lower left with width and
				// height same as the window
				GLU glu = new GLU();

				//"NA PAPIRU" KOORDINATNI SUSTAV
//				glu.gluOrtho2D(0.0f, width, 0.0f, height);
				//BITNO ZA "NORMALNI" MONITOR KOORDINATNI SUSTAV
				glu.gluOrtho2D(0.0f, width, height, 0.0f);

				gl2.glMatrixMode(GL2.GL_MODELVIEW);
				gl2.glLoadIdentity();

				gl2.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
				gl2.glClear(GL.GL_COLOR_BUFFER_BIT);
				gl2.glPointSize(1.0f);
				gl2.glColor3f(0.0f, 0.0f, 0.0f);

			}

			@Override
			public void init(GLAutoDrawable glautodrawable) {
			}

			@Override
			public void dispose(GLAutoDrawable glautodrawable) {
			}

			@Override
			public void display(GLAutoDrawable glautodrawable) {
				GL2 gl2 = glautodrawable.getGL().getGL2();
				gl2.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
				gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

				gl2.glBegin(GL.GL_POINTS);
				BresenhamLDA bresenham = new BresenhamLDA(gl2::glVertex2i);
				model.getLines().forEach(bresenham::drawLine);
				if(firstClick) {
					bresenham.drawLine(new Line(start, end));
				}
				gl2.glEnd();

				if(Objects.nonNull(glLine)) {
					glLine.run();
				}
			}
		};


		protected void addLine() {
			model.add(new Line(start.x, start.y, end.x, end.y));
		}


		@Override
		public void lineAdded(DrawingModel source) {
			repaint();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new LineDrawing2());
	}

}
