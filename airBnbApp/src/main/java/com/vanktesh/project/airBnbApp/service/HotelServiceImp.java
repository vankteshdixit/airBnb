package com.vanktesh.project.airBnbApp.service;

import com.vanktesh.project.airBnbApp.dto.HotelDto;
import com.vanktesh.project.airBnbApp.entity.Hotel;
import com.vanktesh.project.airBnbApp.exception.ResourceNotFoundException;
import com.vanktesh.project.airBnbApp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


//@Service->it tells spring that "this class holds business logic, please create and manage its object(bean) in the Spring container"
@Service
//@Slf4j->it automatically creates a Logger object(Log) for the class
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImp implements HotelService {


    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto){
        log.info("Creating a new hotel with name: {}", hotelDto.getName());
//        convert DTO->Entity
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
//        set default values
        hotel.setActive(false);
//        save into the DB
        hotel = hotelRepository.save(hotel);
        log.info("Created a new hotel with id: {}", hotelDto.getId());
//        Convert Entity → DTO and return it:
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id){
        log.info("Get the hotel with ID: {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with ID:"+id));
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto){
        log.info("Updating the hotel with ID: {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with ID:"+id));
        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
//        modelMapper.map(source, destination)
//        all the things are tansfered from source to destination
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);
    }
    public void deleteHotelById(Long id){
        boolean exists = hotelRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Hotel not found with ID:"+id);

        hotelRepository.deleteById(id);
//        TODO: delete the future inventories for this hotel
    }
}