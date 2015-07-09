# MessageBox Library

### Setup 

##### 1.first import project as android application.
##### 2.use this permission in ```manifest.xml```
``` xml
  <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
```
##### 3.then copy ```icomoon.ttf``` in assets folder.

### Usage
``` java
   MessageBox.makeMessage(getApplicationContext(), "Hello word", MessageBox.LENGTH_SHORT)
                .show();
```
### Option
``` java
   MessageBox.makeMessage(getApplicationContext(), "Hello word", MessageBox.LENGTH_SHORT)
                .setIcon(R.string.ic_clock)
                .setGravity(Gravity.Center)
                .show();
```



  
