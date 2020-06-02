class snorlax {
  constructor(lectureForms, lectures, credit) {
    this.lectureForms = lectureForms;
    this.lectures = lectures;
    this.credit = credit;
    this.displayLectures = {};
  

  }

  // TODO: 강의 배치 로직 구현
  execute() {
    this.lectureForms.forEach((lectureForm) => {
      const lecture = this.lectures[lectureForm];
      const time='1234';
      const times = time.split(',');
      const weekdays=['월', '화', '수', '목', '금'];
      
      
            //const key = `${weekday}${hours}`;
            times.forEach((time) => {
             
              for(let i=0; i<4; i++){
               var hours = time.replace(/\s/g, '').split('')[i];
              if ( hours) {
                for(let j=0; j<5; j++){
                  
                const key = `${weekdays[j]}${hours}`;
                this.displayLectures = {
                  ...this.displayLectures,
                  [key]: {
                    name: lecture.name,
                    professor: lecture.professor,
                    location: lecture.location,
                    isRequired: lecture.isRequired,
                    
                    hours
                  }
                };
              }}}
            });
            
        });

    
        return this.displayLectures;
  
 }
  };
export default snorlax;
