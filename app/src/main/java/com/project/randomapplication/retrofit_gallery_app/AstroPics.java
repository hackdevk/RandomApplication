package com.project.randomapplication.retrofit_gallery_app;

class AstroPics {
    String copyright;
    String date;
    String explanation;
    String hdurl;
    String mediaType;
    String title;
    String imageUrl;
//    String imageType;
    String serviceVersion;

    //for getting only thr details
    public AstroPics(String copyright, String imageUrl, String date, String explanation, String mediaType, String title, String serviceVersion) {
        this.copyright = copyright;
        this.imageUrl = imageUrl;
        this.date = date;
        this.explanation = explanation;
        this.mediaType = mediaType;
        this.title = title;
//        this.imageType = imageType;
        this.serviceVersion = serviceVersion;
    }

    public AstroPics(String imageUrl, String explanation) {
        this.explanation = explanation;
        this.imageUrl = imageUrl;
    }

    public AstroPics(String copyright, String date, String explanation, String hdurl, String mediaType, String title,
                     String imageUrl, String serviceVersion) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.title = title;
        this.imageUrl = imageUrl;
//        this.imageType = imageType;
        this.serviceVersion = serviceVersion;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

//    public String getImageType() {
//        return imageType;
//    }
//
//    public void setImageType(String imageType) {
//        this.imageType = imageType;
//    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }


}
