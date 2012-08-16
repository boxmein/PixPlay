#PixPlay
*A small Processing project to use when wanting to try out random algorithms.*  
Based on Java and Processing.   
Required libraries: [Processing](http://processing.org/download/), [Java](http://www.java.com/en/)


##Controls
  **Space:** Pause / Unpause simulation  
  **Left click:** Draw element  
  **Right click:** Delete element  
  **Middle click:** Pick element  
  **Up arrow:** Make brush bigger by 2px  
  **Down arrow:** Make brush smaller by 2px  
  **1:** Select PixExample  
  **2:** Select PixPowder  
  **3:** Select PixWall  
  
##Elements
  
  **ID 1:** 
    **Laser** - this element just moves left. Color: red

  **ID 2:**
    **Powder** - this element moves down like a powder. 
    It also has a nice lightning strike effect when moving down.
    Color: green
    
  **ID 3:** 
    **Wall** - this element behaves as a wall. Color: black
    
    
##Buttons
  **L**: Selects Laser element  
  **P**: Selects Powder element  
  **W**: Selects Wall element  
  **C**: Clears the screen  
  **H**: Shows the starting message  

##Notes
  While playing, the cursor is hidden. While paused, the cursor is shown.  

##How to compile? (With Eclipse!)
  1. Get the entire source code by either **cloning** or **downloading**.  
  2. Get Eclipse for Java (Regular), if you haven't already.  
  3. Get [Proclipsing for Eclipse](http://code.google.com/p/proclipsing/).  
  4. To export, click the **fancy P icon** left of the test / debug icons and select either **Applet** or **Application**, with the only build configuration there. The finished files will have their own directory in the project root.  
  5. Zip up the contents of the directories, not the directories themselves!  
  (PS. If you haven't got them, add the export directories to **.gitignore**)
##How to compile? (With other IDE's)
  1. Get the entire source by either **cloning** or **downloading**.
  2. Open your IDE  
  3. Make a new project, select **"From existing source"** and then point towards your copy of the source code.  
  4. You'll get a lot of errors. That's because you haven't "linked" the **Processing libraries** yet.  
  5. Find your **Processing directory** (Not your sketches directory, it's where you extracted Processing) and inside, the folder "lib/".   
  6. Inside lib/, find **"core.jar"**.  
  7. Open up your IDE and right click your project. Select something similar to **"Add other archives / libraries..."** and add your core.jar to the list.  
  8. Try debugging.   
  
  How to add JAR-s in NetBeans: Select **Project Properties - Libraries - Add JAR/Folder**



