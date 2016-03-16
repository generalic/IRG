package hr.fer.zemris.irg.vjezba1.task4;

import java.util.ArrayList;
import java.util.List;

public class CustomModel implements DrawingModel {

	private List<Line> lines = new ArrayList<>();
	private List<DrawingModelListener> listeners = new ArrayList<>();

	@Override
	public int getSize() {
		return lines.size();
	}

	@Override
	public Line get(int index) {
		return lines.get(index);
	}

	@Override
	public void add(Line object) {
		lines.add(object);
		listeners.forEach(l -> l.objectsAdded(this));
	}

	@Override
	public void addDrawingModelListener(DrawingModelListener l) {
		if(!listeners.contains(l)) {
			listeners.add(l);
		}
	}

	@Override
	public void removeDrawingModelListener(DrawingModelListener l) {
		listeners.remove(l);
	}

}
