import java.awt.*;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;





public class Main {
    private static final long ms_interval = 1000;

    public static void main(String[] args) {
        Timer timer = new Timer();
        WidgetMetric widget = new WidgetMetric();
        widget.startWidget();


        timer.scheduleAtFixedRate(new MonitorTaskCPU(widget),0,ms_interval);

    }

        private static class MonitorTaskCPU extends TimerTask{

            WidgetMetric widgetMetric;
            MonitorTaskCPU(WidgetMetric widgetMetric){
               setWidgetMetric(widgetMetric);
            }

            public void setWidgetMetric(WidgetMetric widgetMetric){
                this.widgetMetric = widgetMetric;
            }
            public WidgetMetric getWidgetMetric(){
                return this.widgetMetric;
            }

            @Override
            public void run(){
                OperatingSystemMXBean osBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
                double cpuUsage = osBean.getSystemCpuLoad() * 100;
                widgetMetric.setCpuLabel(delimit(cpuUsage));

                double freeMemory = parse(osBean.getFreePhysicalMemorySize());
                widgetMetric.setFreeRamLabel(delimit(freeMemory));

                double maxMemory = parse(osBean.getTotalPhysicalMemorySize());
                widgetMetric.setMaxRamLabel(delimit(maxMemory));

                double usedMemory = (maxMemory - freeMemory);
                widgetMetric.setUsedRAMLabel(delimit(usedMemory));





                System.out.println("**Metrics**");
                System.out.println("----------");
                System.out.println("+CPU use: " + delimit(cpuUsage) + "%");
                System.out.println("+RAM use: " +  delimit(usedMemory) +" GB / "+delimit(maxMemory)+" GB");
                System.out.println("+Free RAM: " + delimit(freeMemory)+" GB");
            }

            //Parse from bytes to GigaBytes
            private double parse(double size){
                return size / (1024 * 1024 * 1024);
            }

            //Allows delimit a value
            private String delimit(double value){
                DecimalFormat formato = new DecimalFormat();
                formato.setMaximumFractionDigits(2);
                return formato.format(value);
            }

        }
}



