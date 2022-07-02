Vue.createApp({
    el: '#app',
    data() {
        return {
            projectNames: [
                {id: 0, title: 'コンデナーイ', url: 'https://kondenai.jp/'},
                {id: 1, title: '新請求システム', url: 'https://www.nittsu.co.jp/'},
                        ],
            subTitles: ['Congestion detection AI service', 'Billing system'],
            frontend: ['HTML5', 'CSS3', 'Bootstrap5', 'JavaScript', 'JQuery', 'Vue.js', 'Python', 'DJango'],
            backend: ['C++', 'Java', 'PHP', 'JavaScript', 'Node.js', 'Python', 'Tensorflow', 'Keras', 'MySQL', 'PostgreSQL'],
            development: ['waterfall', 'agile'],
            hoverFlag: false,
            srcName: '',
            message: '',
        }
        
    },
    methods : {
        mouseOverAction(name, id){
            this.hoverFlag = true;
            this.srcName = 'img/' + name + '.png';
            this.message = this.subTitles[id];
        },
        mouseLeaveAction(){
            return this.hoverFlag = false;
        },
        getSVG(dir, fileName){
            return 'img/' + dir + '/' + fileName + '.svg';
        },
    }
}).mount('#app');