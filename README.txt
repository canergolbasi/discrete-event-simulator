what is DES?
Enter, Wikipedia: “A discrete-event simulation (DES) models the operation of a system as
a (discrete) sequence of events in time. Each event occurs at a particular instant in time and
marks a change of state in the system. Between consecutive events, no change in the system
is assumed to occur; thus the simulation time can directly jump to the occurrence time of
the next event”. In other words, DES is the representation of a certain process by simulating
certain events which occur at certain times. Hence, time is not continuous in DES and progress
according to the events. As a demonstrative example, a process of taking a coffee can be
simulated as follows:
• TIME: 09:00 Go into the checkout queue.
• TIME: 09:05 Order your coffee and make your payment.
• TIME: 09:10 Go into the serving queue.
• TIME: 09:15 Wait for the preparation of your coffee and enjoy your free time.
• TIME: 09:20 Grab your coffee and enjoy!
Note that in DES the only things that count are events and everything else is ignored. In
the case of the customer, until 09:20 only 5 things happened. Additionally, the time progressed
in a discrete fashion (i.e. jumped from one event to the next one). 1
In this project, you are expected to simulate training procedure of ExcelFed,
which is a tennis club founded by his excellency

Do you know his excellency? This is not even a question, everyone knows him. Yes, I am talking
about Roger Federer2 who is the best tennis player ever! He has decided to retire since he is
not young enough to continue his career. However, he wants to transfer his great experience to
the new generation. That’s why, he has founded ExcelFed.
Roger is very experienced so he knows almost everything that plays a huge role in the development
of the tennis player. He has already hired great nutritionists and chefs. Additionally,
he has a consulting team consists of Rafael Nadal and Novak Djokovic. Furthermore, he has
rent nice tennis courts. His aim is to train children in very good conditions so that there are
better tennis players.
There is one “little” problem though. He needs great training coaches, masseurs, and
physiotherapists but he does not know how many he needs. He may hire many staffs but this
would be a waste of money and there are not many outstanding staffs. Therefore, he decided
to simulate the training procedure of ExcelFed with fictional players and staffs to
find how many of them would be enough. However, his relationship with simulation tools
and coding is terrible so he needs your help! Let Roger provide more detail.
• There might be multiple training coaches, masseurs and physiotherapists depending on
the simulation configuration. In this case, every physiotherapist has his/her own service
time and training, and massage duration depending on the player’s need.
• There are exactly three queues in the system: one for training, one for massage and one
for physiotherapy. So, each coach shares a common queue and similar for the others.
• The training queue works in first-come-first-served fashion. Thus, the first player to
enter the queue is served before the others. If two players arrive at the same time, the
one with the lower ID is served first. When the training of the player is finished, he/she
enters the physiotherapy queue, immediately.
• Since more training time requires more urgent rehabilitation, physiotherapy queue works
in a prioritized manner. Therefore, in this queue, the more the training time, the
higher the priority. If there are more than one player that have the same training time, then the one that arrived earlier is served before. If they arrived at the same time
as well, then the one with the lower ID is served first. Note that, one player may come
to the training more than one time, for the prioritization, you should consider only the
current training time not the cumulative.
• Because massage is an advanced service, players should deserve them by their skills.
Hence, in the massage queue, the higher the skill level, the earlier the service. If
there are more than one player that are in the same skill level, then the one that arrived
earlier is served before. If they arrived at the same time as well, then the one with the
lower ID is served first
• For all of the services, players visit the first available staff for the service (the available
staff with the smallest ID) when they leave the queue.