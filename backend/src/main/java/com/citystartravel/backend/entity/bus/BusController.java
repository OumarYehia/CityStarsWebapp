package com.citystartravel.backend.entity.bus;


import com.citystartravel.backend.payload.response.ApiResponse;
import com.citystartravel.backend.payload.response.PagedResponse;
import com.citystartravel.backend.security.CurrentUser;
import com.citystartravel.backend.security.UserPrincipal;
import com.citystartravel.backend.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BusService busService;

    private static final Logger logger = LoggerFactory.getLogger(BusController.class);

    @GetMapping("/getAllBuses")
    public PagedResponse<Bus> getBuses(@CurrentUser UserPrincipal currentUser,
                                       @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return busService.getAllBuses(currentUser, page, size);
    }

    @GetMapping("/getBus")
    public Bus getBus(@CurrentUser UserPrincipal currentUser,
                      @RequestParam(value = "id") Long id) {
        return busService.getBusById(id, currentUser);
    }

    @PostMapping("/createBus")
    public ResponseEntity<?> createBus(@CurrentUser UserPrincipal currentUser,
                                      @RequestBody BusRequest busRequest) {
        try{
            Bus bus = busService.createBus(busRequest, currentUser);
            return ResponseEntity.ok(bus);
        }
        catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity(
                    new ApiResponse(false,"Unable to create bus."),HttpStatus.BAD_REQUEST);
        }

    }
}