package hr.fer.zemris.irg.vjezba1.task4;

public interface DrawingModel {

	int getSize();

	Line get(int index);

	void add(Line object);

	void addDrawingModelListener(DrawingModelListener l);

	void removeDrawingModelListener(DrawingModelListener l);

}