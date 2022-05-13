# travel_buddy
CSCI 380- Software Engineering Project (Spring 2022)

Travel scheduler app

The application uses Model View ViewModel architecture. All packages have a model class where data fields are stored. The data is binded in the adapter package where all the UI are initailzied. In the ViewModel adapter is called and displayed in the main activity. 
To run the program, download the zip file and build on android studio. Once build is complete, run the application on an android emulator or a phyhsical device (Note: No Iphones This is a native android app). Emulators are provided in Android studio, make sure to run the latest SDK's version. To run on a physical device install vyzor which will build the app on the phone. 
Once activity is started, splash screen will popup and take you to login. Go ahead and register or sign up with google. In the home page, there are 3 components. First one takes you to a questionnaire which will ask some questions and process it and take ur answers and fetch data based on your answers. Then based on loaded destinations, you can add the destinations to your iterinary list. It is saved in cloud firestore.

You can view saved trips on the second components where upcoming trips will be loaded. One can cancel and complete iterinary as based on the activity. 
You can also view your history and flag trips in the past. Other components include about us and explore yourself which takes in device location and fetches destinations based on your current location.
There is also a notification reminder which sends you notification one week before the trip.
