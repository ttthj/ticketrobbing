public class System {
    public static void main(String[] args) {
        System ticketMachine = new System();
        Ticket ticket = ticketMachine.new Ticket(20);//设置余票数
        TicketRobber ticketRobber1 = ticketMachine.new TicketRobber("TicketRobber1", ticket);
        TicketRobber ticketRobber2 = ticketMachine.new TicketRobber("TicketRobber2", ticket);
        new Thread(ticketRobber1).start();
        new Thread(ticketRobber2).start();
    }

    //余票
    class Ticket {
        int num;

        public Ticket(int num) {
            this.num = num;
        }
    }


    class TicketRobber implements Runnable {
        String name;
        Ticket ticket;

        public TicketRobber(String name, Ticket ticket) {
            this.name = name;
            this.ticket = ticket;
        }


        @Override
        public void run() {
            while (true) {
                synchronized (ticket) {
                    if (ticket.num > 0) {
                        java.lang.System.out.println(name + ":您成功抢到一张票，余票剩余" + (--ticket.num));
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        java.lang.System.out.println(name + ":余票不足，购买失败");
                        break;
                    }
                }
            }
        }
    }


    }
