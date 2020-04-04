# Calendar
Calendar is a console based aplication intended as a way to help user manage their time. It allows user to create events associated
with date and time, and to later view those events. It supports multiple profiles and features a scheduler that lets you plan a
meeting so that selected people can attend.
# Origin
I wrote this program as a project for an university course. It was meant to showcase the principles of object-oriented programming
in practice.
# Class specification
* Event and classes that inherit from it - Represent events
* Time - Represents time and allows for time management
* Day - Stores events, provides an interface for managing events in a given day
* Date - Represents date, used to associate days with dates and includes tools for date formatting
* Calendar - Stores days associated with events, provides an interface for managing them, represents a profile
* CalendarMenu - Provides a user interface and manages profiles
* MeetingScheduler - Responsible for planning meetings, used by CalendarMenu.

**Class diagram**
![Class diagram](https://github.com/AdrianUrbanski/Kalendarz/blob/master/DiagramKlas.png)
