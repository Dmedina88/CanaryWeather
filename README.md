# CanaryWeather

Task:
Imagine that you are working for a new start­up that deals with weather forecasts. 
You are responsible for building an Android that displays the weather forecast of a user location. 
The app will gather the users location and hit an API (ex: WeatherUnderground, Dark Sky) to display the next 7 days. 
Also, a user will have the ability to see a more detailed view of the weather for each of those days
Functionality:
Your app should contain the following functionality
­ The 7 day forecast should be displayed using a View Pager
­ The app should use an sqlite database and display the data while the phone is offline
­ The transition to the detailed view should use a custom animation
­ Proper unit tests for all models and utility functions.

# design decisions 
The overall app architecture is MVVM. I think MVVM offers a lot more safty and meaningful division between the view and data layers then MVC or MVP. I also think it's much simpler and easier then MVI which I feel would have added a lot of boilerplate for no reason.

For the data layer, I used the repository pattern to coordinate interaction between the network and database. I made interfaces for every class in the data layer; this both makes things easier to change if the database changes or use a different network api. It also makes the code more mockable for easter tests. 

I used coroutines and livedata for observation and moving my code off the main thread. This will keep the app much cleaner and simpler. I considered changing the ForecasteReposity to monitor database changes but it didn't seem worth it as the requirements didn't really need anything that complex. Since we never update the database at any other point, it didn't seem worth changing the code I had to add that feature. While I have a lot of RX experience, I personally think coroutines and livedata are likely a better solution for most projects, as RX can be overkill and I have seen RX get out of hand in some apps.

I used Koin for DI since it's easier than dagger and way more idiomatic. Although Dagger may be a little bit more performant when getting initialized, it would be insignificant in such a tiny app.. The DI also helps keep the code testable and easy to read.
