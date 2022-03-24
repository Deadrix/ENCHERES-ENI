package fr.eni.projetEncheres.model.dal;

import java.util.List;

import fr.eni.projetEncheres.model.bo.Category;

public interface CategoryDAO {

	public List<Category> selectAll ();
		
}
