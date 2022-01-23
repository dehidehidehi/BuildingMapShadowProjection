package BuildingShadows;

import java.util.List;

import BuildingShadows.DAO.BuildingCSVDAO;
import BuildingShadows.DAO.IBuildingDAO;
import BuildingShadows.DAO.Entities.Building;
public class Main {

	public static void main(String[] args) throws Exception {
		IBuildingDAO reader  = new BuildingCSVDAO();
		List<Building> buildings = reader.readAll();
		buildings.forEach(b -> System.out.println(b));
		buildings.get(0).getCoordinates().getLongDegrees();
	}
}
