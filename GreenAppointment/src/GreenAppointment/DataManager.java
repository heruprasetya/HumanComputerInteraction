package GreenAppointment;

/** Created by GuoJunjun <junjunguo.com> on 31/01/15. */

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * DataManager is a static class:
 * <p/>
 * manages all the data appointments & user information
 */
public class DataManager {
    private static ArrayList<Appointment> appointments;

    public DataManager() {
        appointments = new ArrayList<Appointment>();
        //        System.out.println("loadData: " + loadData());
    }

    /**
     * add the given appointment to appointments array list
     *
     * @param appointment
     */
    public static void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
     * remove the given appointment from the appointments array list
     *
     * @param appointment
     */
    public static void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    /**
     * @return appointments as an array list
     */
    public static ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * save appointments to file
     *
     * @return true is succeed, false otherwise
     */
    public static boolean saveData() {
        //        try {
        //            FileOutputStream savefile = new FileOutputStream("appointmentSavedFile.txt");
        //            ObjectOutputStream save = new ObjectOutputStream(savefile);
        //            save.writeObject(appointments);
        //            System.out.println("save appointment: " + appointments.get(0).toString());
        //
        //            save.close();
        //            return true;
        //        } catch (FileNotFoundException e) {
        //            e.printStackTrace();
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        //        return false;
        return convertAndSavetoJson();
    }

    /**
     * convert and save object to json
     *
     * @return @return true is succeed, false otherwise
     */
    public static boolean convertAndSavetoJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("appointmentSavedFile.json"), appointments);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * load appointments from file
     *
     * @return true is succeed, false otherwise
     */
    public static boolean loadData() {
        //        ArrayList<Appointment> ap;
        //        try {
        //            FileInputStream savedfile = new FileInputStream("appointmentSavedFile.txt");
        //            ObjectInputStream object = new ObjectInputStream(savedfile);
        //            ap = (ArrayList<Appointment>) object.readObject();
        //            System.out.println(ap.toString() + " length: " + ap.size());
        //            if (ap.get(0).getFormal() == null) {
        //                System.out.println("null Appointment");
        //            } else {
        //                appointments = ap;
        //                System.out.println("out print appointment: " + appointments.get(0).toString());
        //            }
        //            return true;
        //        } catch (FileNotFoundException e) {
        //            e.printStackTrace();
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        } catch (ClassNotFoundException e) {
        //            e.printStackTrace();
        //        }
        //        return false;
        return JsontoJavaObject();
    }

    /**
     * read and convert from Json string file to java object
     *
     * @return true if succeed, false otherwise
     */
    public static boolean JsontoJavaObject() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayList al = mapper.readValue(new File("appointmentSavedFile.json"), ArrayList.class);
            System.out.println("read as arraylist: " + al.toString());
            List<Appointment> myObjects = mapper.readValue(
                    new File("appointmentSavedFile.json"), mapper.getTypeFactory().constructCollectionType(
                            List.class, Appointment.class));
            System.out.println("my other objects: " + myObjects.toString());
            //            appointments = (ArrayList<Appointment>) myObjects;
            return true;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return false;
    }
}
