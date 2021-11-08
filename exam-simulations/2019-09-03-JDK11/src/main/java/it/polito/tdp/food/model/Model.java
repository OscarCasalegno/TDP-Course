package it.polito.tdp.food.model;

import java.util.List;

import it.polito.tdp.food.db.FoodDao;

public class Model {

	FoodDao dao = new FoodDao();

	public List<String> getTipiPorzione() {
		return dao.listPortionTypes();
	}

}
