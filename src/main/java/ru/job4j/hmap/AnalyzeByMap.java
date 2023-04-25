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
        double sumLocal = 0;
        int countLocal = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                sumLocal += subject.score();
                countLocal++;
            }
            pupilsList.add(new Label(pupil.name(), sumLocal / countLocal));
            sumLocal = 0;
            countLocal = 0;
        }
        return pupilsList;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> subjectList = new ArrayList<>();
        Set<String> subjectSet = new HashSet<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                subjectSet.add(subject.name());
            }
        }
        double sumLocal = 0;
        int countLocal = 0;
        for (String subjectOuter : subjectSet) {
            for (Pupil pupil : pupils) {
                for (Subject subjectInner : pupil.subjects()) {
                    if (subjectInner.name().equals(subjectOuter)) {
                        sumLocal += subjectInner.score();
                        countLocal++;
                    }
                }
            }
            subjectList.add(new Label(subjectOuter, sumLocal / countLocal));
            sumLocal = 0;
            countLocal = 0;
        }
        return subjectList;
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
        double maxPupilsScore = pupilsList.get(0).score();
        int indexOfBestPupil = 0;
        for (int i = 1; i < pupilsList.size(); i++) {
            if (pupilsList.get(i).score() > maxPupilsScore) {
                indexOfBestPupil = i;
            }
        }
        return pupilsList.get(indexOfBestPupil);
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
        double maxSubjectScore = subjectsList.get(0).score();
        int indexOfBestSubject = 0;
        for (int i = 1; i < subjectsList.size(); i++) {
            if (subjectsList.get(i).score() > maxSubjectScore) {
                indexOfBestSubject = i;
            }
        }
        return subjectsList.get(indexOfBestSubject);
    }
}
