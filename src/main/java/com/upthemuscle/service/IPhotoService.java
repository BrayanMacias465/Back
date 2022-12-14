package com.upthemuscle.service;

import com.upthemuscle.model.Photo;

import java.util.List;

public interface IPhotoService {
    Photo savePhoto(Photo photo);

    List<Photo> getAllPhotos();

    Photo getPhoto(String photoId);

    void updatePhoto(Photo photo);

    void deletePhoto(String photoId);
}
