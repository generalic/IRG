package hr.fer.zemris.irg.vjezba1.task4.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.irg.vjezba1.task4.Line;

public class CustomModel implements DrawingModel {

	private List<Line> lines = new ArrayList<>();
	private List<DrawingModelListener> listeners = new ArrayList<>();

	@Override
	public void add(Line line) {
		lines.add(line);
		listeners.forEach(l -> l.lineAdded(this));
	}

	@Override
	public List<Line> getLines() {
		return Collections.unmodifiableList(lines);
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
