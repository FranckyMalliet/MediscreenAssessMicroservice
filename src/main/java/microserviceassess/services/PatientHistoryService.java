package microserviceassess.services;

import microserviceassess.beans.Patient;
import microserviceassess.beans.PatientHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PatientHistoryService {

    private final static Logger logger = LoggerFactory.getLogger(PatientHistoryService.class);
    private final List<String> triggerFactorList;

    public PatientHistoryService(){
        triggerFactorList = createTermsList();
    }

    public String assessmentGenerator(Patient patient, List<PatientHistory> patientHistoryList){
        int factorLevel = calculatePatientFactorLevel(patientHistoryList);
        int age = determineAge(patient.getBirthDate());
        String dangerLevel = determinePatientDangerLevel(factorLevel, patient.getGender(), age);
        System.out.println(determinePatientDangerLevel(factorLevel, patient.getGender(), age));

        System.out.println(patient.getGender() + " " + age);

        String message = "Patient : " + patient.getFirstName() + " " + patient.getLastName()
                + " Test : Test" + dangerLevel
                + " (age " + age + ")"
                + " diabetes assessment is: " + dangerLevel;

        logger.info(message);
        return message;
    }

    private int calculatePatientFactorLevel(List<PatientHistory> patientHistoryList){
        int factorLevel = 0;

        for(PatientHistory patientHistory : patientHistoryList){
            String notes = patientHistory.getNotes();

            for(String factors : triggerFactorList){
                if(Pattern.compile(Pattern.quote(factors), Pattern.CASE_INSENSITIVE).matcher(notes).find()){
                    factorLevel ++;
                }
            }
        }

        System.out.println("factorLevel is : " + factorLevel);
        return factorLevel;
    }

    private String determinePatientDangerLevel(int dangerLevel, String gender, int age) {

        if(dangerLevel == 2 & age > 30){
            return "Borderline";
        } else if (dangerLevel == 8 & age > 30){
            return "Early onset";
        } else if (dangerLevel >= 7 & age < 30 & gender.equals("F")){
            return "Early onset";
        } else if (dangerLevel >= 5 & age < 30 & gender.equals("M")){
            return "Early onset";
        } else if (dangerLevel >= 6 & age > 30){
            return "In Danger";
        } else if(dangerLevel >= 4 & age < 30 & gender.equals("F")){
            return "In Danger";
        } else if(dangerLevel >= 3 & age < 30 & gender.equals("M")){
            return "In Danger";
        } else {
            return "None";
        }
    }

    private int determineAge(Date birthDate){
        Calendar birthDay = Calendar.getInstance();
        birthDay.setTime(birthDate);

        long currentTime = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(currentTime);

        int age = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        return age;
    }

    private List<String> createTermsList() {
        List<String> termsList = new ArrayList<>();
        termsList.add("Hémoglobine A1C");
        termsList.add("Microalbumine");
        termsList.add("Taille");
        termsList.add("Poids");
        termsList.add("Fumeur");
        termsList.add("Anormal");
        termsList.add("Cholestérol");
        termsList.add("Vertige");
        termsList.add("Rechute");
        termsList.add("Réaction");
        termsList.add("Anticorps");

        return termsList;
    }
}
