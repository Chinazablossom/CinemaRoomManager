package cinema

import kotlin.IndexOutOfBoundsException

/*
--Description:
Running a cinema theatre is no easy business. To help our friends, let's add statistics to your program.
The stats will show the current income, total income, the number of available seats, and the percentage of occupancy.

In addition, our friends asked you to take care of a small inconvenience:
 it's not good when a user can buy a ticket that has already been purchased by another user. Let's fix this!

--Objectives:
Now your menu should look like this:

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
When the item Statistics is chosen, your program should print the following information:

The number of purchased tickets;
The number of purchased tickets represented as a percentage. Percentages should be rounded to 2 decimal places;
Current income;
The total income that shows how much money the theatre will get if all the tickets are sold.
The rest of the menu items should work the same way as before,
except the item Buy a ticket shouldn't allow a user to buy a ticket that has already been purchased.

If a user chooses an already taken seat, print That ticket has already been purchased! and ask them to enter different seat coordinates,
until they pick an available seat. Of course, you shouldn't allow coordinates that are out of bounds.
If this happens, print Wrong input! and ask to enter different seat coordinates until the user picks an available seat.

Please note that you need to output percentages with 2 digits after the separator. For this output, you can do this:

val percentage = 0.0
val formatPercentage = "%.2f".format(percentage)
print(formatPercentage) // 0.00
Examples
The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

Enter the number of rows:
> 6
Enter the number of seats in each row:
> 6

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 3

Number of purchased tickets: 0
Percentage: 0.00%
Current income: $0
Total income: $360

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 2

Enter a row number:
> 1
Enter a seat number in that row:
> 1

Ticket price: $10

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 3

Number of purchased tickets: 1
Percentage: 2.78%
Current income: $10
Total income: $360

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 2

Enter a row number:
> 1
Enter a seat number in that row:
> 1

That ticket has already been purchased!

Enter a row number:
> 10
Enter a seat number in that row:
> 20

Wrong input!

Enter a row number:
> 4
Enter a seat number in that row:
> 4

Ticket price: $10

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 1

Cinema:
  1 2 3 4 5 6
1 B S S S S S
2 S S S S S S
3 S S S S S S
4 S S S B S S
5 S S S S S S
6 S S S S S S

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 3

Number of purchased tickets: 2
Percentage: 5.56%
Current income: $20
Total income: $360

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
> 0
 */

import kotlin.properties.Delegates

private var row by Delegates.notNull<Int>()
private var seats by Delegates.notNull<Int>()
private var yourRow by Delegates.notNull<Int>()
private var yourSeat by Delegates.notNull<Int>()
private var purchasedTickets = 0
private var currentIncome = 0
private var totalIncome = 0
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
            3 -> statistics()
        }

    } while (yourChoice != 0)

}

private fun menuList() {
    println()
    println(
        "1. Show the seats\n" +
        "2. Buy a ticket\n" +
        "3. Statistics\n" +
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
    try {
        println("Enter a row number:")
        yourRow = readln().toInt()
        println("Enter a seat number in that row:")
        yourSeat = readln().toInt()
        println()
        val priceFrontHalf = 10
        val priceSecondHalf = 8
        val totalNumberOfSeats = row * seats

        //if the ticket has already been purchased
        if (cinema[yourRow - 1][yourSeat - 1] == "B") {
            println("That ticket has already been purchased!")
            buyATicket()
            return
        }

        if (totalNumberOfSeats <= 60 && cinema[yourRow - 1][yourSeat - 1] != "B") {
            ticketPrice = priceFrontHalf
            println("Ticket price: $$ticketPrice")
        } else if (totalNumberOfSeats > 60 && yourRow > row / 2) {
            ticketPrice = priceSecondHalf
            println("Ticket price: $$ticketPrice")
        } else {
            ticketPrice = priceFrontHalf
            println("Ticket price: $$ticketPrice")
        }

        // if the seats the user inputed are out of bound
    } catch (e: IndexOutOfBoundsException) {
        println("Wrong input!")
        buyATicket()
    }

    cinema[yourRow - 1][yourSeat - 1] = "B"      // taken seat
    currentIncome += ticketPrice            //currentIncome
    purchasedTickets++                 //purchased tickets

}

private fun statistics() {
    val percentage = (purchasedTickets * 1.0f) /(row*seats)  * 100
    val formatPercentage = "%.2f".format(percentage)                //percentage

// total income
    val totalNumberOfSeats = row * seats
    if (totalNumberOfSeats <= 60) {
        totalIncome = row * seats * 10
    } else if (totalNumberOfSeats > 60 && row % 2 == 0) {
        totalIncome = (totalNumberOfSeats / 2 * 10) + (totalNumberOfSeats / 2 * 8)
    } else if (totalNumberOfSeats > 60 && row % 2 == 1) {
        totalIncome = (row / 2 * seats * 10) + ((row - row / 2) * seats * 8)
    }

    println(
        "Number of purchased tickets: $purchasedTickets \n" +
        "Percentage: $formatPercentage%\n" +
        "Current income: $$currentIncome\n" +
        "Total income: $$totalIncome"
    )
}









