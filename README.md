# MQWeather App 
The intention of the of the poject name to keep is MQWeather is MQ which adds up the brand value and weather which says what exactly app does, i.e the app provides Weather and this is from mobiquity.

## Design first approach 
For any app design is the key most of the apps will be written as code first then desgin which may not be a good idea, to reduce the itterative development and effective efforts design first approach is the key and I belive in that. With this the probability of missing requirements are very less. 


#### My approach 
Started with wireframes on paper rough sketches then to get a better idea on what I am thinking on my mind converted into designs with the limited knowledge on Figma. and here are the mockups and designs came out as first cut. This gave me to digest and gave confidence on requirements. 

Below are the rough mockups and designs 

![Alt text](https://github.com/naveenkow/MQWeather_app/blob/master/First_Design_wireframes.jpeg?raw=true "Wireframes") ![Alt text](https://github.com/naveenkow/MQWeather_app/blob/master/imgpsh_fullsize_anim%20copy.png?raw=true "Dashboard") ![Alt text](https://github.com/naveenkow/MQWeather_app/blob/master/imgpsh_fullsize_anim.png?raw=true "Details")

## Architecture : 
My options on architecture for this application is clean architecture with MVVM or MVVM. But I picked plain MVVM, the reason behind is clean architeure can be great advantage for any scalable application but with the requirements to satisify MVVM is the berfect suit. However we have always advantage of easily incorporate MVVM into clean architeture. 

### Why MVVM ?
* In a architectural point of view MVVM give great flexibility to modularizing the comonents and where the code can be easily testable and maintainable. 
* The recent Jetpack components that are being evolving are around MVVM architecture. 
* Since the Jetpack components are more reactive in nature MVVM modular design can easily bind with the concepts. 
* Recent trend in Android Development MVVM became so popular and google also encouraging to use it which cannot be much deviatinon interms of future recommondations. 
* It also provides much flexibility to incorporate SOLID priciples easily. 

### TDD 
-  TDD is is best approach test the code 
- Added integration tests to test end points 
