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
               // 만약에 한글이면 배열에 넣어, 숫자가 나오면 그 배열 1개랑 그 숫자랑 합쳐 월1 -> 결과 배열에 넣어, 한글 -> 배열 1개 pop 한글을 push '수'
                for(let i=0; i<4; i++){
                 var hours = time.split(' ')[i]; // 월  1  월  2  수10
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