# MessageBox Library

### Setup 

##### 1.First import project as android application.
##### 2.Use this permission in ```manifest.xml```
``` xml
  <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
```
##### 3.Then copy ```icomoon.ttf``` in assets folder.

-----

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

### Setup IconFont
##### 1.New project ```IconFont``` in [icomoon] (https://icomoon.io).
##### 2.Select icons from icomoon's library.
##### 3.Generate font.
##### 4.Copy ```icomoon.tff``` in assets folder.
##### 5.Enter IconFont character as string element in ``` strings.xml ``` | res > values > strings.xml

``` xml
    <string name="ic_image"></string>
    <string name="ic_camera"></string>
    <string name="ic_headphones"></string>
    <string name="ic_connection"></string>
```

----

### Tools 
[icomoon] (https://icomoon.io)

----

### Developer
#### [Mohammad Hossein Kashizadeh](mailto:mh.kashizadeh@gmail.com)

  
