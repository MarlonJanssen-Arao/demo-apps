package tech.arao.multithreading.producerconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class MultithreadedProducerConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(MultithreadedProducerConsumer.class);

    private final static Integer PRODUCER_TIMEOUT = 250;
    private final static Integer CONSUMER_TIMEOUT = 1000;


    public static void main(String[] args ) {
        final ProducerConsumer task = new ProducerConsumer();

        Thread producer = new Thread(new Runnable() {
            public void run() {
                try {
                    task.produce();
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e.getCause());
                }
            }
        });
        Thread consumer = new Thread(new Runnable() {
            public void run() {
                try {
                    task.consume();
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e.getCause());
                }
            }
        });

        try {
            producer.start();
            consumer.start();
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
    }


    public static class ProducerConsumer {

        int capacity = 10;
        LinkedList<Integer> jobNumbers = new LinkedList<Integer>();


        public void produce() throws InterruptedException {
            int value = 0;

            while(true) {
                synchronized (this) {
                    while (jobNumbers.size() == capacity) {
                        wait();
                    }

                    System.out.format("PRODUCER >>> current job number: %d%n", value);
                    jobNumbers.add(value++);
                    notify();
                    Thread.sleep(PRODUCER_TIMEOUT);
                }
            }
        }

        public void consume() throws InterruptedException {
            while(true) {
                synchronized (this) {
                    while (jobNumbers.size() == 0) {
                        wait();
                    }

                    int firstJob = jobNumbers.removeFirst();

                    System.out.format("CONSUMER >>> current job number: %d%n", firstJob);

                    notify();
                    Thread.sleep(CONSUMER_TIMEOUT);
                }
            }
        }
    }
}
