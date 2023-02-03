const SPORTS = ['sports', '스포츠'];
const BASEBALL = ['baseball', '야구'];
const FOOTBALL = ['soccer', '축구'];
const BASKETBALL = ['basketball', '농구'];

const FINANCE = ['finance', '금융'];
const STOCK = ['stocks', '주식'];
const CRYPTO = ['virtualcurrency', '가상화폐'];
const REALESTATE = ['realestate', '부동산'];

const GAME = ['game', '게임'];
const LOL = ['leagueoflegends', 'LOL'];
const STARCRAFT = ['starcraft', '스타크래프트'];
const BOARDGAME = ['boardgame', '보드게임'];

const SOCIAL = ['social', '사교'];
const LIQUOR = ['drinking', '음주'];
const PARTY = ['party', '파티'];
const TRAVEL = ['travel', '여행'];

const CULTURE = ['artculture', '문화'];
const MOVIES = ['movie', '영화'];
const MUSIC = ['music', '음악'];
const BOOKS = ['book', '도서'];

const CREATIVITY = ['creationmaking', '창작'];
const KNITTING = ['knitting', '뜨개질'];
const CRAFTING = ['craft', '공예'];
const DRAWING = ['drawing', '드로잉'];

const HOME = ['/', 'Home'];
const SHOWCASE = ['/', 'Showcase'];
const ALL = ['/posts', 'All'];

const MAIN = [HOME, SHOWCASE, ALL];

const TAGNAME_DICT = {
  baseball: '야구',
  soccer: '축구',
  basketball: '농구',
  stocks: '주식',
  virtualcurrency: '가상화폐',
  realestate: '부동산',
  leagueoflegends: 'LOL',
  starcraft: '스타크래프트',
  boardgame: '보드게임',
  drinking: '음주',
  party: '파티',
  travel: '여행',
  movie: '영화',
  music: '음악',
  book: '도서',
  knitting: '뜨개질',
  craft: '공예',
  drawing: '드로잉',
};

const CATEGORIES = [
  [SPORTS, BASEBALL, FOOTBALL, BASKETBALL],
  [FINANCE, STOCK, CRYPTO, REALESTATE],
  [GAME, LOL, STARCRAFT, BOARDGAME],
  [SOCIAL, LIQUOR, PARTY, TRAVEL],
  [CULTURE, MOVIES, MUSIC, BOOKS],
  [CREATIVITY, KNITTING, CRAFTING, DRAWING],
];

const TAGS = CATEGORIES.reduce((acc, cur) => {
  cur.forEach((el, idx) => {
    if (idx >= 1) acc.push(el);
  });
  return acc;
}, []);

export { MAIN, CATEGORIES, TAGS, TAGNAME_DICT };
