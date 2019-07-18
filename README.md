
## Part A - Fix current bugs

### Bug 1 - Layout does not look as expected

Views in activity_login.xml were unarranged, therefore I deleted all the constraints from the views and re-arranged the views in the  activity_login.xml using Layout Editor, so that it match the expected layout. 

### Bug 2 - Validation is incorrect

First I checked if email and password field are empty , if it is empty then it will show error on the field.
If Email and Password field don't match the pattern it will show error on the field. If input matches pattern error are removed. If user enter valid email and password (androidtest@moneyboxapp.com : P455word12), user will promted to UserAccountActivity. If the user enter email and password that is not registered then toast message will pop up saying Account not found. Name field is allowed to be empty but if user enter name then it should match name pattern .If user enter name it  will be shown in UserAccountActivity. 

### Bug 3 - Animation is looping incorrectly

For the first part of animation, I set the min frame to 0 and max frame to 109 by calling setMinAndMaxFrame(0, 109) method and called playAnimation() to play the animation.

for the second part of animation I called Animator listener and override method onAnimationEnd(animation: Animator) to find end of animation. And then I set the min frame to 131 and max frame to 158 by calling setMinAndMaxFrame(131, 158). And to loop the second part of animation I called repeatMode method.

## Part B

Code are organised in MVP Artitecture. I chose MVP Artitecture mainly for separation of concern aswel as it is easier for testing as all the business logic will be in presenter. Presenter gets the data from the model and pass it to the view.

All the entities are in model package , service package contain ApiService interface and Interactor for the api service, UI package contain Activity , presenter and view interface for all the three screens and dagger package contain app component and module. ApiInteractor is used to interact with the Api.

Libraries used for this application Retrofit, RxJava2 and Dagger2. 

- Retrofit library is used to retrieve and upload JSON data.
- Rxjava is used to do asynchronous operation while doing network calls.
- Dagger2 is used for dependency injection to provide shared instance in different part of application.

### Screen 2 - User accounts screen

if the user enter valid email and password (androidtest@moneyboxapp.com : P455word12) then User will be able see User account screen that shows account information such as TotalPlanValue, accounts that the user holds such as Stocks and Shares ISA , General Investment Account , Lifetime ISA , planValue and Moneybox total for all account. These are displayed in Recyclerview.

In order to see these Product data Bearer token is need , this token is provided when user enter valid email and password to login.Intent is used to passed the token from Login Activity to User Account activity. UserAccountPresenter getUserData(token: String) method use this token to retrieve the investorproducts data from the api.


### Screen 3 - Individual account screen

When user clicks on the one of the account ,Individual account screen will open that shows details such as name of account , planvalue and moneybox total. These data where pass using intent from UserAccountActivity. User can also to add £10 to their Moneybox total. UpdateAmount() method in IndividualAccountPresenter is called when Add £10 button is clicked. This method than sends post request to the api and Moneybox total will be updated. At first Moneybox value in user account is not updated but if you close and reopen the app the value will be updated.
