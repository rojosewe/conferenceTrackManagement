Requirements:

Maven2

Run:

mvn compile

mvn test

mvn exec:java -Dexec.mainClass="com.thoughtworks.trackmanagement.ConferenceTrackManagement" -Dexec.args="src/test/resources/ValidFile.txt"

Where src/test/resources/ValidFile.txt is the file with the talks' specification
according to the problem's statement.

Design:

The program is built in Java.
 
The conference planner can be seen as a bin packing problem. An algorithm 
must be chosen from the many possible solutions. We programed a greedy algorith 
with a best fit decreasing approach. This will not return the optimal answer but 
it will five a good answer of no more than 11/9 * optimal bins + 1. Giving a 
very fast answer.

The best fit looks to minimize the number of tracks by putting talks where the 
waste of time (time with no talks waiting for lunch or networking) is minimized.

A Session (Track) is comprised of Two TalkSets: The MorningSession and AfternoonSession.
A conference is basically a list of Sessions.

A Talk, Lunch and Networking Event are considered Events. Lunch and Networking Event
Are englobed by MiscEvent.

The file is read by the ConferenceFileReader and passes a list of Strings to
the TalkParser. The TalkParser must return a list of valid Talks or throw an InvalidTalkException
if it's not possible.
  
ConferencePlanner impelments the bin packing algorithm and builds a Conference
(List of Sessions) with the best fit possible according to our choice of algorithm.

The ConferenceSerializer is used to print a conference by track and with real hours
of the day.

Problem: Conference Track Management
 
You are planning a big programming conference and have received many proposals 
which have passed the initial screen process but you're having trouble fitting 
them into the time constraints of the day -- there are so many possibilities! 
So you write a program to do it for you.

The conference has multiple tracks each of which has a morning and afternoon session.
* Each session contains multiple talks.
* Morning sessions begin at 9am and must finish by 12 noon, for lunch.
* Afternoon sessions begin at 1pm and must finish in time for the networking event.
* The networking event can start no earlier than 4:00 and no later than 5:00.
* No talk title has numbers in it.
* All talk lengths are either in minutes (not hours) or lightning (5 minutes).
* Presenters will be very punctual; there needs to be no gap between sessions.
 
Note that depending on how you choose to complete this problem, your solution 
may give a different ordering or combination of talks into tracks. This is acceptable; 
you don't need to exactly duplicate the sample output given here.
 
Test input:
Writing Fast Tests Against Enterprise Rails 60min
Overdoing it in Python 45min
Lua for the Masses 30min
Ruby Errors from Mismatched Gem Versions 45min
Common Ruby Errors 45min
Rails for Python Developers lightning
Communicating Over Distance 60min
Accounting-Driven Development 45min
Woah 30min
Sit Down and Write 30min
Pair Programming vs Noise 45min
Rails Magic 60min
Ruby on Rails: Why We Should Move On 60min
Clojure Ate Scala (on my project) 45min
Programming in the Boondocks of Seattle 30min
Ruby vs. Clojure for Back-End Development 30min
Ruby on Rails Legacy App Maintenance 60min
A World Without HackerNews 30min
User Interface CSS in Rails Apps 30min
 
Test output: 
Track 1:
09:00AM Writing Fast Tests Against Enterprise Rails 60min
10:00AM Overdoing it in Python 45min
10:45AM Lua for the Masses 30min
11:15AM Ruby Errors from Mismatched Gem Versions 45min
12:00PM Lunch
01:00PM Ruby on Rails: Why We Should Move On 60min
02:00PM Common Ruby Errors 45min
02:45PM Pair Programming vs Noise 45min
03:30PM Programming in the Boondocks of Seattle 30min
04:00PM Ruby vs. Clojure for Back-End Development 30min
04:30PM User Interface CSS in Rails Apps 30min
05:00PM Networking Event
 
Track 2:
09:00AM Communicating Over Distance 60min
10:00AM Rails Magic 60min
11:00AM Woah 30min
11:30AM Sit Down and Write 30min
12:00PM Lunch
01:00PM Accounting-Driven Development 45min
01:45PM Clojure Ate Scala (on my project) 45min
02:30PM A World Without HackerNews 30min
03:00PM Ruby on Rails Legacy App Maintenance 60min
04:00PM Rails for Python Developers lightning
05:00PM Networking Event

 