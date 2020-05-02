import com.hufsSchedule.hufsScheduleSystem.GrdCond.CreditCond.CreditCondObj;
import com.hufsSchedule.hufsScheduleSystem.GrdCond.GrdCourseService;

import java.util.List;
import java.util.stream.Collectors;

public class GrdCompareService {
    private CreditCondObj remainCredit;
    private List<String> remainCourseList;

    public GrdCompareService(List<String> userCourseList, List<String> grdCourseList, Credit userCredit, CreditCondObj grdCredit) {
        this.remainCourseList = compareCourseList(userCourseList, grdCourseList);
        this.remainCredit = compareCredit(userCredit, grdCredit);
    }

    public List<String> compareCourseList(List<String> userCourseList, List<String> grdCourseList) {
        List<String> resultCourseList = grdCourseList.stream().filter(aObject ->
                !userCourseList.contains(aObject)).collect(Collectors.toList());
        return resultCourseList;
    }

    public CreditCondObj compareCredit(Credit userCredit, CreditCondObj grdCredit) {
        CreditCondObj remainCredit = new CreditCondObj();

        remainCredit.setFirstMajor(userCredit.getFirstMajor() - grdCredit.getFirstMajor());
        remainCredit.setSecondMajor(userCredit.getSecondMajor() - grdCredit.getSecondMajor());
        remainCredit.setSubMajor(userCredit.getSubMajor() - grdCredit.getSubMajor());
        remainCredit.setMinor(userCredit.getMinor() - grdCredit.getMinor());
        remainCredit.setOutDoor(userCredit.getOutdoor() - grdCredit.getOutDoor());
        remainCredit.setLiberalArts(userCredit.getLiberalArts() - grdCredit.getLiberalArts());
        remainCredit.setTeaching(userCredit.getTeaching() - grdCredit.getTeaching());
        remainCredit.setOptional(userCredit.getOptional() - grdCredit.getOptional());
        remainCredit.setTotalCredit(userCredit.getTotalCredit() - grdCredit.getTotalCredit());

        return remainCredit;
    }
}
