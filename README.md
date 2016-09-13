# DerpCounter
A simple Java Application to display an image on screen with text above it and a counter to count how often you pressed the hotkey.
Works even if the application is not the active window.

##How to install

Download the [Derpcounter.jar](https://github.com/Plagiatus/DerpCounter/raw/master/DerpCounter.jar) from the main Branch and execute it. That's it. As easy as that. No Installation, no hassle, just run the file and press P on your keyboard.

##Example

Here is an example on how it will look:
The Popup Image will always be in the middle of the screen, you can place the GUI wherever you want.

![example](https://cloud.githubusercontent.com/assets/7681159/18480749/1c01db02-79da-11e6-875a-087784bde371.png)


##GUI Overview

![explanation](https://cloud.githubusercontent.com/assets/7681159/18480614/9421ba36-79d9-11e6-999e-4c4535f3e1db.png)


# More explanation and stuff you should remember

##Image
###Selected Image
The Programm comes with the shown image as default, but you can always choose your own image. Pressing the button will present you with a standard file selector.
Make sure your selected Image has the desired size since the Program will use the image you provide, as is. It won't even crop it to fit your screen.

**It supports animated gifs.**
*Note: they won't play from the beggining. They will loop around in the background and will just show whatever comes now.*

###Duration
The duration describes how long the image will be shown.
It's in seconds, use a Point to switch to fractions of a second. After half of the time it will count up.

*You can't overlap the counters. So if you press 3 times in quick succession it will show all 3, one after the other.*

*Make sure to save your settings by pressing the button*

##Text
###Display Text
The text you'd like to have displayed.

*Make sure to save your settings by pressing the button*

###Text Color
Clicking this button will open up a simple Color Chooser. Just select the color you want and click **OK*.
The little square will show you what color you have selected right now.

###Text Size
This will change the size of the displayed Text. Remember 

*Make sure to confirm by pressing the button

## Stuff that isn't implemented (yet)

- Support for transparent images
- changing the position of the overlay image
- saving your custom settings
- support for full-screen applications
- change amount of delay before changing of the number
- make it possible to run the counter before the image disappears again
- different Fonts as well as bold/italic
