public class Board {
    //2D Array of Cell objects
    public Cell[][] Boardgame;
    //1D Array of Boat objects
    public Boat[] boatObjects;
    //keep track of total shots
    public int shot;
    //keep track of turns;
    public int turn;
    //keep track of ships remaining
    public int ships;
    //rows on the board
    public int boardRow;
    //columns on the board
    public int boardCol;

    // CONSTRUCTOR
    public Board(int m, int n) {
        // Declare board size with given inputs
        if (m >= 3 && m <= 10 && n >= 3 && n <= 10) {
            this.boardRow = m;
            this.boardCol = n;
            this.Boardgame = new Cell[boardRow][boardCol];
            //instantiate the board with blank cell objects
            for (int col = 0; col < boardCol; col++) {
                for (int row =0; row < boardRow; row++) {
                    this.Boardgame[row][col] = new Cell(row, col, ' ');
                }
            }
        }
        // Add specified number of ships
        if (this.boardRow == 3 || this.boardCol ==3) {
            this.ships = 1;
        }
        else if ((this.boardRow <=4 && this.boardRow > 3)||(this.boardCol <= 4 && this.boardCol>3)) {
            this.ships = 2;
        }
        else if ((this.boardRow <=6 && this.boardRow > 4)||(this.boardCol <= 6 && this.boardCol>4)) {
            this.ships = 3;
        }
        else if ((this.boardRow <=8 && this.boardRow > 6)||(this.boardCol <= 8 && this.boardCol>6)) {
            this.ships = 4;
        }
        else if ((this.boardRow <=10 && this.boardRow > 8)||(this.boardCol <= 10 && this.boardCol>8)) {
            this.ships = 5;
        }
        else { System.out.println("Board size is out of range, try new values.");}
        // Declare 1D array of boat objects
        this.boatObjects = new Boat[this.ships];
    }

  //getter function for the number of ships
    public int getships() {return this.ships;}

    //Set size of baots based on how many
    public void placeBoats() {
      // Generates an array representing diffferent sizes of boats based on how many boats are needed
      int[] boatSizes;
      switch(this.ships) {
        case 1:
          boatSizes = new int[] = {2};
          break;
        case 2:
          boatSizes = new int[] = {2, 3};
          break;
        case 3:
          boatSizes = new int[] = {2, 3, 3};
          break;
        case 4:
          boatSizes = new int[] = {2, 3, 3, 4};
          break;
        case 5:
          boatSizes = new int[] = {2, 3, 3, 4, 5};
          break;
      }
      // Generate a random point and check to see if a boat can fit there, if it can place it down
      int boatsPlaced = 0;
      while (boatsPlaced < this.ships) {
        // Randomly select coordinate pair and orientation
        int xcor = (int)Math.floor(this.boardRow * Math.random());
        int ycor = (int)Math.floor(this.boardCol * Math.random());
        double directionNum = Math.random()
        if (directionNum > 0.5)
          boolean orientation = false; // Vertical
        else {boolean orientation = true;} // Horizontal
        // Check for open spaces
        int openSpaceCount = 0;
        if (orientation) {
          for (int xcorCheck = 0; xcorCheck < boatSizes[boatsPlaced]; xcorCheck++) {
            if (xcor + boatSizes[boatsPlaced]-1 < this.boardRow) {
              if (this.boardGame[xcor + xcorcheck][ycor].get_status() == ' ')
                openSpaceCount++;
            }
          }
          if (openSpaceCount == boatSizes[boatsPlaced]) {
            Cell[] newBoatCells = new Cell[boatSizes[boatsPlaced]];
            for (int xcorVar = 0; xcorVar < boatSizes[boatsPlaced]; xcorVar++) {
              newBoatCells[xcorVar] = this.boardGame[xcor + xcorVar][ycor];
              this.boardGame[xcor + xcorVar][ycor].set_status('B');
            }
            Boat newBoat = new Boat(boatSizes[boatsPlaced], newBoatCells, true);
            boatsPlaced++;
          }
        } else {
            for (int ycorCheck = 0; ycorCheck < boatSizes[boatsPlaced]; ycorCheck++) {
              if (ycor + boatSizes[boatsPlaced]-1 < this.boardCol) {
                if (this.boardGame[xcor][ycor + ycorcheck].get_status() == ' ')
                  openSpaceCount++;
              }
            }
            if (openSpaceCount == boatSizes[boatsPlaced]) {
              Cell[] newBoatCells = new Cell[boatSizes[boatsPlaced]];
              for (int ycorVar = 0; ycorVar < boatSizes[boatsPlaced]; ycorVar++) {
                newBoatCells[ycorVar] = this.boardGame[xcor][ycor + ycorVar];
                this.boardGame[xcor][ycor + ycorVar].set_status('B');
              }
              Boat newBoat = new Boat(boatSizes[boatsPlaced], newBoatCells, false);
              boatsPlaced++;
            }
        }
      }
    }

    // Fire method for firing one off shots
    // Set conditions to ensure the inputted values are correct
    public void fire(int x, int y) {
      if (x < 0 || y < 0 || x >= this.boardRow || y >= this.boardCol) {
        System.out.println("PENALTY: Out of bounds input");
      } else if (this.boardGame[x][y].get_status() == 'B'){
        this.boardGame[x][y].set_status() = 'H';
        System.out.println("Hit!")
      } else if (this.boardGame[x][y].get_status() == ' '){
        this.boardGame[x][y].set_status() = 'M';
        System.out.println("Miss..")
      } else {
        System.out.println("PENALTY: Already fired upon");
      }
    }

    //print out the player board state every turn
    public void display() {

    }
    //print out the fully revealed board if a player types in the print command (debugging purposes)
    public void print() {

    }
    //fire a missile on a specified coordinate check the coordiantes
    //error message if its not in the coordinates
    public void missile(int x, int y) {
        if (this.boardRow < x && x >= 0 && this.boardCol < y && y >= 0) {
          // Keep track of overall hits and misses
          int hits = 0;
          int misses = 0;
          // Arrays of all distinct cooridinates to be hit in missile strike
          int[] xcorTargets = new int[] = {x-1, x, x+1};
          int[] ycorTargets = new int[] = {y-1, y, y+1};
          // Loop through all potentially valid coordinates
          for (int xInd = 0; x < xcorTargets.length; xInd++) {
            for (int yInd = 0; x < ycorTargets.length; yInd++) {
              // Filter out coordinates outside board
              if (this.boardRow < xcorTargets[xInd] && xcorTargets[xInd] >= 0 && this.boardCol < ycorTargets[yInd] && ycorTargets[yInd] >= 0) {
                // Tag as hit or misses
                if (this.boardGame[xcorTargets[xInd]][ycorTargets[yInd]].get_status() == 'B'){
                  this.boardGame[xcorTargets[xInd]][ycorTargets[yInd]].set_status() = 'H';
                  hits++;
                } else if (this.boardGame[xcorTargets[xInd]][ycorTargets[yInd]].get_status() == ' '){
                  this.boardGame[xcorTargets[xInd]][ycorTargets[yInd]].set_status() = 'M';
                  misses++;
                }
              }
            }
          }
          System.out.println("Missile hits: " + String.valueOf(hits));
          System.out.println("Missile misses: " + String.valueOf(misses));

        } else { System.out.println("Error: Invalid coordinates"); }
    }

    // scan a specific row or column
    // error message if its not in the coordinates
    public void drone(int direction, int index) { // direction 1:horizontal, 0:vertical
      // Track number of boats in scan
      int boatCount = 0;
      if (direction == 1 && this.boardCol < index && index >= 0) { // Horizontal (x-direction or row-wise)
        for (int xcor = 0; xcor < this.boardRow; xcor++;) {
          if (this.boardGame[xcor][index].get_status() == 'B')
            boatCount++;
        }
      } else if (direction == 0 && this.boardRow < index && index >= 0) {
        for (int ycor = 0; ycor < this.boardCol; ycor++;) {
          if (this.boardGame[index][ycor].get_status() == 'B')
            boatCount++;
        }
      } else { System.out.println("Error: Invalid coordinates"); }
      // If sweep was executed print total number of boats in row or column
      if (boatCount) { System.out.println("There were " + String.valueOf(boatCount) + " boats spotted in sweeep")}
    }

}
