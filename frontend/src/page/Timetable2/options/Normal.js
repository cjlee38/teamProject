class Normal {
  constructor(lectureForms, lectures) {
    this.lectureForms = lectureForms;
    this.lectures = lectures;
    this.displayLectures = {};
  }

  execute() {
    this.lectureForms.forEach((lectureForm) => {
      console.log(lectureForm, this.lectures[0])
      const lecture = this.lectures[lectureForm];

      if (lecture.time) {
        const times = lecture.time.split(',');

        times.forEach((time) => {
          const regx = '[^0-9]'
          const weekday = regx.exec(time);
          console.log(weekday)
          const hours = time.replace(/\s/g, '').split('')[1];

          if (weekday && hours) {
            const key = `${weekday}${hours}`;

            this.displayLectures = {
              ...this.displayLectures,
              [key]: {
                name: lecture.name,
                professor: lecture.professor,
                location: lecture.location,
                isRequired: lecture.isRequired,
                weekday,
                hours
              }
            };
          }
        });
      }
    });

    return this.displayLectures;
  }
}

export default Normal;
