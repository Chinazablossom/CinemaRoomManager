package cinema

import kotlin.properties.Delegates

/*
--Description:
The theatre is getting popular, and the customers started complaining that it's not practical that the program stops after buying one ticket.
Let's add a menu that will allow them to buy tickets and display the current state of the seating arrangement when needed.

--Objectives:
At the start, your program should read two positive integer numbers that represent the number of rows and seats in each row.
Then, it should print a menu with the following three items:

'Show the seats' should print the current seating arrangement. The empty seats should be marked with an S symbol, and taken seats are marked with a B symbol.
'Buy a ticket' should read the seat coordinates from the input and print the ticket price like in the previous stage.
After that, the chosen seat should be marked with a B when the item Show the seats is called.
Exit should stop the program.

--Example:
The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

Enter the number of rows:
> 7
Enter the number of seats in each row:
> 7

1. Show the seats
2. Buy a ticket
0. Exit
> 1

Cinema:
  1 2 3 4 5 6 7
1 S S S S S S S
2 S S S S S S S
3 S S S S S S S
4 S S S S S S S
5 S S S S S S S
6 S S S S S S S
7 S S S S S S S

1. Show the seats
2. Buy a ticket
0. Exit
> 2

Enter a row number:
> 4
Enter a seat number in that row:
> 5
Ticket price: $10

1. Show the seats
2. Buy a ticket
0. Exit
> 1

Cinema:
  1 2 3 4 5 6 7
1 S S S S S S S
2 S S S S S S S
3 S S S S S S S
4 S S S S B S S
5 S S S S S S S
6 S S S S S S S
7 S S S S S S S

1. Show the seats
2. Buy a ticket
0. Exit
> 2

Enter a row number:
> 6
Enter a seat number in that row:
> 6
Ticket price: $10

1. Show the seats
2. Buy a ticket
0. Exit
> 1

Cinema:
  1 2 3 4 5 6 7
1 S S S S S S S
2 S S S S S S S
3 S S S S S S S
4 S S S S B S S
5 S S S S S S S
6 S S S S S B S
7 S S S S S S S

1. Show the seats
2. Buy a ticket
0. Exit
> 0

 */


private var row by Delegates.notNull<Int>()
private var seats by Delegates.notNull<Int>()
private var yourRow by Delegates.notNull<Int>()
private var yourSeat by Delegates.notNull<Int>()
private var ticketPrice = 0
private lateinit var cinema: MutableList<MutableList<String>>


fun main() {
    println("Enter the number of rows:")
    row = readln().toInt()
    println("Enter the number of seats in each row:")
    seats = readln().toInt()
    cinema = MutableList(row) { MutableList(seats) { "S" } }

    do {
        menuList()
        val yourChoice = readln().toInt()
        println()
        when (yourChoice) {
            1 -> showTheSeat()
            2 -> buyATicket()
        }

    } while (yourChoice != 0)


}

private fun menuList() {
    println()
    println(
        "1. Show the seats\n" +
        "2. Buy a ticket\n" +
        "0. Exit"
    )
    println()
}

private fun showTheSeat() {
    println("Cinema:")
    for (numberOfSeats in 1..cinema[0].size) {
        print(" $numberOfSeats")
    }
    println()
    for (numberOfRows in 1..cinema.size) {
        println("$numberOfRows ${cinema[numberOfRows - 1].joinToString(" ")}")
    }
}


private fun buyATicket() {
    println("Enter a row number:")
    yourRow = readln().toInt()
    println("Enter a seat number in that row:")
    yourSeat = readln().toInt()
    val priceFrontHalf = 10
    val priceSecondHalf = 8
    val totalNumberOfSeats = row * seats

    if (totalNumberOfSeats <= 60) {
        ticketPrice = priceFrontHalf
        println("Ticket price: $$ticketPrice")
    } else if (totalNumberOfSeats > 60 && yourRow > row / 2) {
        ticketPrice = priceSecondHalf
        println("Ticket price: $$ticketPrice")
    } else {
        ticketPrice = priceFrontHalf
        println("Ticket price: $$ticketPrice")
    }

    cinema[yourRow - 1][yourSeat - 1] = "B"
}


