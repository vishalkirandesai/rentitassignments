//import java.util.Date;
//import java.util.List;
//
//import org.joda.time.DateTime;
//import org.joda.time.Days;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.rentit.main.Plant;
//import com.rentit.main.PurchaseOrder;
//import com.rentit.main.exception.InvalidHirePeriodException;
//import com.rentit.main.exception.PlantUnavailableException;
//import com.rentit.main.util.POStatus;
//import com.rentit.repository.PlantRepository;
//import com.rentit.rest.PurchaseOrderResource;
//
//@Service
//public class PurchaseOrderService {
//	@Autowired
//	PlantRepository plantRepository;
//
//	public PurchaseOrder createPO(PurchaseOrderResource por) throws 
//	PlantUnavailableException, InvalidHirePeriodException { 
//
//		if(por.getStartDate().compareTo(por.getEndDate()) >= 0) 
//			throw new InvalidHirePeriodException("Invalid date range"); 
//
//		List<Plant> plants = plantRepository.findBetweenDateRange(por.getPlantId().getName(),
//																por.getStartDate(), 
//																por.getEndDate()); 
//
//		if(!plants.isEmpty()){ 
//			PurchaseOrder po = new PurchaseOrder(); 
//
//			// Set state in the state diagram 
//			po.setStatus(POStatus.ACCEPTED); 
//			po.setEndDate(new Date(por.getEndDate())); 
//			po.setStartDate(new Date(por.getStartDate())); 
//			po.setPlantId(plants.get(0)); 
//
//			DateTime startDate = new DateTime(por.getStartDate()); 
//			DateTime endDate = new DateTime(por.getEndDate()); 
//			int days = Days.daysBetween(startDate, endDate).getDays(); 
//
//			po.setPrice(days * po.getPlantId().getPrice()); 
//			po.persist(); 
//
//			return po; 
//		}else 
//			throw new PlantUnavailableException("The requested plant is not available"); 
//	}}