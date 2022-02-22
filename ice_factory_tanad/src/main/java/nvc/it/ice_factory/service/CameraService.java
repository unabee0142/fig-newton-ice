package nvc.it.ice_factory.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import nvc.it.ice_factory.model.Camera;
import nvc.it.ice_factory.model.Event;
import nvc.it.ice_factory.repository.CameraRepository;



@Service
public class CameraService {
    
    @Autowired
    private CameraRepository cameraRepository ;

    public List<Camera> getCameras(){
        return cameraRepository.findAll();
    }

    public Optional<Camera> findById(String id){
        return cameraRepository.findById(id);
    }

    public List<Camera> findCameraByName(String name){
        return cameraRepository.findByNameContaining(name);
    }

    public Camera addCamera(Camera camera){
        return cameraRepository.save(camera);
    }

    public Optional<Camera> updateCamera(String id, Camera camera){
        Camera currentCamera = cameraRepository.findById(id).get();
        currentCamera.setName(camera.getName());
        currentCamera.setLocation(camera.getLocation());
        return Optional.of(cameraRepository.save(currentCamera));
    }

    public Optional<Camera> addEvent(String id,Event event){
        Camera currentCamera = cameraRepository.findById(id).get();
        List<Event> events = currentCamera.getEvent();
        event.setCreatedAt(new Date(System.currentTimeMillis()));
        events.add(event);
        currentCamera.setEvent(events);

        return Optional.of(cameraRepository.save(currentCamera));
    }





    public boolean deleteCamera(String id){
        try {
            cameraRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
