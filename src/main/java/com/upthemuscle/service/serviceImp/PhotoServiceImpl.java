package com.upthemuscle.service.serviceImp;

import com.upthemuscle.model.Photo;
import com.upthemuscle.repository.PhotoRepository;
import com.upthemuscle.service.IPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PhotoServiceImpl implements IPhotoService {

    private final PhotoRepository photoRepository;

    @Override
    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public List<Photo> getAllPhotos() {
        List<Photo> photoEntityList = photoRepository.findAll();
        return photoEntityList;
    }

    @Override
    public Photo getPhoto(String photoId) {
        return photoRepository.findById(photoId).get();
    }

    @Override
    public void updatePhoto(Photo photo) {
        photoRepository.save(photo);
    }

    @Override
    public void deletePhoto(String photoId) {
        photoRepository.deleteById(photoId);
    }
}
