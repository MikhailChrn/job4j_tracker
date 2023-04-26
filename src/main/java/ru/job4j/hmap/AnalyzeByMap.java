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
                if (!subjectMap.containsKey(subject.name())) {
                    subjectMap.put(subject.name(), subject.score());
                } else {
                    subjectMap.put(subject.name(), subjectMap.get(subject.name()) + subject.score());
                }
            }
        }
        List<Label> subjectList = new ArrayList<>();
        
/*        List<Label> subjectList = new ArrayList<>();
        Set<String> subjectSet = new HashSet<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                subjectSet.add(subject.name());
            }
        }

        for (String subjectOuter : subjectSet) {
            double sumLocal = 0;
            int countLocal = 0;
            for (Pupil pupil : pupils) {
                for (Subject subjectInner : pupil.subjects()) {
                    if (subjectInner.name().equals(subjectOuter)) {
                        sumLocal += subjectInner.score();
                        countLocal++;
                    }
                }
            }
            subjectList.add(new Label(subjectOuter, sumLocal / countLocal));
        }
        return subjectList;*/
        return null;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> pupilsList = new ArrayList<>();
        double pupilScoreSum = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                pupilScoreSum += subject.score();
            }
            pupilsList.add(new Label(pupil.name(), pupilScoreSum));
            pupilScoreSum = 0;
        }
        Collections.sort(pupilsList);
        return pupilsList.get(pupilsList.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> subjectsList = new ArrayList<>();
        Set<String> subjectSet = new HashSet<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                subjectSet.add(subject.name());
            }
        }
        double sumLocal = 0;
        for (String subjectOuter : subjectSet) {
            for (Pupil pupil : pupils) {
                for (Subject subjectInner : pupil.subjects()) {
                    if (subjectInner.name().equals(subjectOuter)) {
                        sumLocal += subjectInner.score();
                    }
                }
            }
            subjectsList.add(new Label(subjectOuter, sumLocal));
            sumLocal = 0;
        }
        Collections.sort(subjectsList);
        return subjectsList.get(subjectsList.size() - 1);
    }
}
