package BuildingShadows.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import BuildingShadows.DAO.Entities.Building;
import BuildingShadows.Objects.GPSCoordinates;

public class BuildingCSVDAO implements IBuildingDAO {

	private String fileName = "io_building_coordinates.csv";
	private String basePath = "src/main/resources/";
	private BufferedReader reader;

	public BuildingCSVDAO() throws FileNotFoundException, UnsupportedEncodingException {
		File f = new File(this.getBasePath() + this.getFileName());
		FileInputStream stream = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(stream, "UTF-8");
		this.reader = new BufferedReader(isr);
	}

	public List<Building> readAll() throws JsonProcessingException, IOException {
		return convert(this.readCSV());
	}

	private List<Building> convert(List<Map<String, String>> contents) {
		ArrayList<Building> convertedBuildings = new ArrayList<>();
		for (Map<String, String> csvBuildingEntry : contents) {
			Building building = new Building();
			building.setCoordinates(new GPSCoordinates(csvBuildingEntry.get("Coordinates")));
			building.setCountry(csvBuildingEntry.get("Country"));
			building.setHeight(this.extractPinnacleHeight(csvBuildingEntry.get("Pinnacle height")));
			building.setName(csvBuildingEntry.get("Name"));
			building.setTown(csvBuildingEntry.get("Town"));
			convertedBuildings.add(building);
		}
		return convertedBuildings;
	}

	/**
	 * converts "829.8 m (2,722 ft)" to double 829.8
	 */
	private double extractPinnacleHeight(String pinnacleHeight) {
		// Watch out for odd ascii whitespaces which are not handled by .split()
		String stringDouble = pinnacleHeight.trim().split("m")[0].trim();
		stringDouble = stringDouble.substring(0, stringDouble.length() - 1);
		return Double.valueOf(stringDouble);
	}

	private List<Map<String, String>> readCSV() throws JsonProcessingException, IOException {
		List<Map<String, String>> response = new LinkedList<Map<String, String>>();
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader();
		MappingIterator<Map<String, String>> iterator = mapper.reader(Map.class).with(schema).readValues(this.reader);
		while (iterator.hasNext()) {
			response.add(iterator.next());
		}
		return response;
	}

	public String getFileName() {
		return fileName;
	}

	public String getBasePath() {
		return basePath;
	}

}
