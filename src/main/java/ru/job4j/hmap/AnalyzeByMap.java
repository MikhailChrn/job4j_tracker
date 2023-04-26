package ru.job4j.hmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double sumLocal = 0;
        int countLocal = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                sumLocal += subject.score();
                countLocal++;
            }
        }
        return sumLocal / countLocal;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> pupilsList = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double sumLocal = 0;
            int countLocal = 0;
            for (Subject subject : pupil.subjects()) {
                sumLocal += subject.score();
                countLocal++;
            }
            pupilsList.add(new Label(pupil.name(), sumLocal / countLocal));
        }
        return pupilsList;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> subjectMap = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                Integer score = subjectMap.getOrDefault(subject.name(), 0);
                subjectMap.put(subject.name(), score + subject.score());
            }
        }
        List<Label> subjectList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : subjectMap.entrySet()) {
            subjectList.add(new Label(entry.getKey(), entry.getValue() / pupils.size()));
        }
        return subjectList;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> pupilsList = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double pupilScoreSum = 0;
            for (Subject subject : pupil.subjects()) {
                pupilScoreSum += subject.score();
            }
            pupilsList.add(new Label(pupil.name(), pupilScoreSum));
        }
        Collections.sort(pupilsList);
        return pupilsList.get(pupilsList.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> subjectMap = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                Integer score = subjectMap.getOrDefault(subject.name(), 0);
                subjectMap.put(subject.name(), score + subject.score());
            }
        }
        List<Label> subjectList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : subjectMap.entrySet()) {
            subjectList.add(new Label(entry.getKey(), entry.getValue()));
        }
        Collections.sort(subjectList);
        return subjectList.get(subjectList.size() - 1);
    }
}
