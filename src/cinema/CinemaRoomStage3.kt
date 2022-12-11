package cinema

/*
--Description:
When choosing a ticket you are guided not only by your space preference but also by your finances.
Let's implement the opportunity to check the ticket price and see the reserved seat.

--Objectives:
Read two positive integer numbers that represent the number of rows and seats in each row and print the seating arrangement like in the first stage.
Then, read two integer numbers from the input: a row number and a seat number in that row.
These numbers represent the coordinates of the seat according to which the program should print the ticket price.
The ticket price is determined by the same rules as the previous stage:

If the total number of seats in the screen room is not more than 60, then the price of each ticket is 10 dollars.
In a larger room, the tickets are 10 dollars for the front half of the rows and 8 dollars for the back half.
Please note that the number of rows can be odd, for example, 9 rows.
In this case, the first half is the first 4 rows, and the second half is the rest 5 rows.
After that, the program should print out all the seats in the screen room as shown in the example and mark the chosen seat by the B symbol.
Finally, it should print the ticket price and stop. Note that in this project, the number of rows and seats won't be greater than 9.

--Examples:
The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

*Example 1

Enter the number of rows:
> 7
Enter the number of seats in each row:
> 8

Cinema:
  1 2 3 4 5 6 7 8
1 S S S S S S S S
2 S S S S S S S S
3 S S S S S S S S
4 S S S S S S S S
5 S S S S S S S S
6 S S S S S S S S
7 S S S S S S S S

Enter a row number:
> 3
Enter a seat number in that row:
> 6

Ticket price: $10

Cinema:
  1 2 3 4 5 6 7 8
1 S S S S S S S S
2 S S S S S S S S
3 S S S S S B S S
4 S S S S S S S S
5 S S S S S S S S
6 S S S S S S S S
7 S S S S S S S S

*Example 2:

Enter the number of rows:
> 8
Enter the number of seats in each row:
> 9

Cinema:
  1 2 3 4 5 6 7 8 9
1 S S S S S S S S S
2 S S S S S S S S S
3 S S S S S S S S S
4 S S S S S S S S S
5 S S S S S S S S S
6 S S S S S S S S S
7 S S S S S S S S S
8 S S S S S S S S S

Enter a row number:
> 6
Enter a seat number in that row:
> 5

Ticket price: $8

Cinema:
  1 2 3 4 5 6 7 8 9
1 S S S S S S S S S
2 S S S S S S S S S
3 S S S S S S S S S
4 S S S S S S S S S
5 S S S S S S S S S
6 S S S S B S S S S
7 S S S S S S S S S
8 S S S S S S S S S

 */

fun main() {
    println("Enter the number of rows:")
    val row = readln().toInt()
    println("Enter the number of seats in each row:\n")
    val seats = readln().toInt()
    val seatSymbol = " S"
    println("Cinema:")
    for (i in 1..seats)
        print(" $i")
    println()
    for (i in 1..row)
        println("$i${seatSymbol.repeat(seats)}")

    println("Enter a row number:")
    val yourRow = readln().toInt()
    println("Enter a seat number in that row:")
    val yourSeat = readln().toInt()
    val seatSymbolB = "B"
    val priceFrontHalf = 10
    val priceSecondHalf = 8
    val ticketPrice = "Ticket price: "
    val totalNumberOfSeats = row * seats

    if (totalNumberOfSeats <= 60) {
        println("$ticketPrice$$priceFrontHalf")
    } else if (totalNumberOfSeats > 60 && yourRow >  row / 2) {
        println("$ticketPrice$$priceSecondHalf")
    }else {
        println("$ticketPrice$$priceFrontHalf")
    }
    println("Cinema:")
    for (numberOfSeats in 1..seats)
        print(" $numberOfSeats")
    println()
    for (numberOfRow in 1..row)
        if (numberOfRow == yourRow)
            print("$numberOfRow${seatSymbol.repeat(yourSeat - 1)} $seatSymbolB${seatSymbol.repeat(seats - yourSeat)}\n")
        else println("$numberOfRow${seatSymbol.repeat(seats)}")

}
