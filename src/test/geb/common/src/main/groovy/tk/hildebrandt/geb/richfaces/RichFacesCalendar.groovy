package tk.hildebrandt.geb.richfaces
import geb.Module
import java.text.SimpleDateFormat

class RichFacesCalendar extends Module {
   static content = {
      openButton { $('img') }
      todayButton { $('div', text: 'Today') }
      yearBackButton { $('div', text: '<<') }
      monthBackButton { $('div', text: '<') }
      monthForwardButton { $('div', text: '>') }
      yearForwardButton { $('div', text: '>>') }
      dayButton { $('td.rf-cal-btn', it) }
      selectedDay(require: false) { $('td.rf-cal-sel').text() }
      input { $('input.rf-cal-inp') }
      selectedMonth { $('td.rf-cal-hdr-month').text() }
   }

   void value(Date date) {
      openButton.click()
      Calendar target = Calendar.getInstance();
      target.setTime(date)
      int year = target.get(Calendar.YEAR);
      while (year < getCurrentYear()) {
         yearBackButton.click()
      }
      while (year > getCurrentYear()) {
         yearForwardButton.click()
      }
      int month = target.get(Calendar.MONTH);
      while (month < getCurrentMonth()) {
         monthBackButton.click()
      }
      while (month > getCurrentMonth()) {
         monthForwardButton.click()
      }
      int dayOfMonth = target.get(Calendar.DAY_OF_MONTH);
      dayButton(dayOfMonth).click()
   }

   private int getCurrentYear() {
      def currentDate = new SimpleDateFormat('MMMMM, yyyy', Locale.US).parse(selectedMonth)
      def instance = Calendar.getInstance()
      instance.setTime(currentDate)
      return instance.get(Calendar.YEAR)
   }

   private int getCurrentMonth() {
      def currentDate = new SimpleDateFormat('MMMMM, yyyy', Locale.US).parse(selectedMonth)
      def instance = Calendar.getInstance()
      instance.setTime(currentDate)
      return instance.get(Calendar.MONTH)
   }

   void today() {
      openButton.click()
      todayButton.click()
   }
}
