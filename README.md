# Shopify-Backend-Developer-Intern-Challenge
> Task: Build an image repository.

This is Image Repository was built with Java/Spring-Boot, Cloudinary & Postgres,documented with Swagger, deployed on heroku. View [Docs](https://imagerepository2021.herokuapp.com/swagger-ui.html#).

### Two Ideas Implemented

- ADD image(s) to the repository
    - one / bulk / enormous amount of images
    - private or public (permissions)
    - secure uploading and stored images
- DELETE image(s)
    - one / bulk / selected / all images
    - Prevent a user deleting images from another user (access control)
    - secure deletion of images

### How To Use

The [Docs](https://imagerepository2021.herokuapp.com/swagger-ui.html#) is pretty comprehensive, but a few issues.

> For ``https://imagerepository2021.herokuapp.com/api/v1/bulk-upload`` for multiple(bulk) upload. File arrays are not supported in OpenAPI 2.0. You need OpenAPI 3.0 to upload multiple files, see [Swagger official docs](https://swagger.io/docs/specification/describing-request-body/file-upload/#multiple). This is why upload functionality for multiple files is not available on the docs.....**USE POSTMAN**

> The `deleteSelectedinBulk` endpoint `/api/v1/images` accepts images id in array request eg  ``https://imagerepository2021.herokuapp.com/api/v1/image?imageIds=1,2,3,4``

> Testing using Postman would be perfect **TOO**!!





### Development


> Java 11, Spring Boot, Maven, Postgres, Swagger, Git, Cloudinary, Heroku
 
