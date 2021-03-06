package hr.fer.zemris.irg.vjezba1.task4.model;

import java.util.List;

import hr.fer.zemris.irg.vjezba1.task4.Line;

public interface DrawingModel {

	void add(Line line);

	List<Line> getLines();

	void addDrawingModelListener(DrawingModelListener l);

	void removeDrawingModelListener(DrawingModelListener l);

}