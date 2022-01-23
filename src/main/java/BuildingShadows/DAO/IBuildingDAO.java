package BuildingShadows.DAO;

import java.util.List;

import BuildingShadows.DAO.Entities.Building;

public interface IBuildingDAO {
	public List<Building> readAll() throws Exception;
}
