# iTunes

This android application is a master-detail app that lists tracks that are retrieved from ITunes Search API.

See iTunes Web Service Documentation: https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching

# M-V-P Architecture

1. **Maintainability**: This patter demonstrates a clear separation of presentation layer from the logic that makes it more easier in understanding and maintenance.
2. **Easily Tested**: It is a user interface pattern which eases automated UT.
3. **Reusability**: It allows you to easily reuse views for building agile and scalable android applications. 

# Data Persistence

1. Stores date of user's last visit on the app.

### Libraries used:

1. Dagger2
2. RxJava2
3. Retrofit2
4. ButterKnife
5. Picasso
6. Timber
7. Gson