const seriesListDummy = {
  data: [
    {
      id: 1,
      title: '제목',
      content: '내용',
      category: '카테고리',
      views: 100,
      thumbnailUrl: 'url~',
      postId: 1,
      totalPosts: 10,
      createdAt: '2023-01-17 12:00:00',
      modifiedAt: '2023-01-17 18:00:00',
      member: {
        id: 1,
        nickname: 'nickname',
        profileImageUrl: 'url',
      },
    },
    {
      id: 2,
      title: '제목',
      content: '내용',
      category: '카테고리',
      views: 100,
      thumbnailUrl: 'url~',
      postId: 2,
      totalPosts: 10,
      createdAt: '2023-01-17 12:00:00',
      modifiedAt: '2023-01-17 18:00:00',
      member: {
        id: 1,
        nickname: 'nickname',
        profileImageUrl: 'url',
      },
    },
    {
      id: 3,
      title: '제목',
      content: '내용',
      category: '카테고리',
      views: 100,
      thumbnailUrl: 'url~',
      postId: 3,
      totalPosts: 10,
      createdAt: '2023-01-17 12:00:00',
      modifiedAt: '2023-01-17 18:00:00',
      member: {
        id: 1,
        nickname: 'nickname',
        profileImageUrl: 'url',
      },
    },
  ],
  pageInfo: {
    page: '1',
    size: '10',
    totalPage: '5',
    totalElements: '50',
  },
};

const PostDummy = {
  data: {
    id: 1, // 포스트 식별자
    title: 'title', // 제목
    content: 'content', // 내용
    views: 100, // 조회수
    category: 'category', // 카테고리
    seriesId: 1, // 시리즈 Id
    isItWriter: false, // 작성자 여부
    createdAt: '2023-01-10 12:00:00', // 작성일
    modifiedAt: '2023-01-10 18:00:00', // 수정일
    writer: {
      // 작성자 정보
      id: 1, // 포스트 작성자 식별자
      nickname: 'nickname', // 포스트 작성자 닉네임
      profileImageUrl: 'url', // 포스트 작성자 프로필 사진
      isSubscribed: false, // 작성자에 대한 구독여부
    },
    comments: [
      // 댓글정보
      {
        id: 1, // 댓글 식별자
        content: 'content', // 댓글 내용
        createdAt: '2023-01-08 12:00:00', // 작성일
        modifiedAt: '2023-01-08 18:00:00', // 수정일
        isItWriter: false, // 작성자여부
        writer: {
          // 댓글 작성자 정보
          id: 1, // 댓글 작성자 식별자
          nickname: 'nickname', // 댓글 작성자 닉네임
          profileImageUrl: 'url', // 댓글 작성자 프로필 사진
        },
      },
    ],
    seriesPosts: ['http://localhost:8080/posts/1', 'http://localhost:8080/posts/2'], // 시리즈에 속해있는 post의 링크
  },
};

const userInfoDummy = {
  id: 1,
  email: 'java@gmail.com',
  nickname: 'nickname',
  introduction:
    '안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요',
  imgUrl: 'url~',
  createdAt: '2023-01-17 12:00:00',
  modifiedAt: '2023-01-17 18:00:00',
};

const userActivitiesDummy = [
  {
    id: 1,
    title: '제목',
    content: '시리즈',
    category: '카테고리',
    views: 100,
    thumbnailUrl: 'url~',
    createdAt: '2023-01-17 12:00:00',
    modifiedAt: '2023-01-17 18:00:00',
    member: {
      id: 1,
      nickname: 'nickname',
      profileImageUrl: 'url',
    },
  },
  {
    id: 2,
    title: '제목',
    content: '시리즈',
    category: '카테고리',
    views: 100,
    thumbnailUrl: 'url~',
    createdAt: '2023-01-17 12:00:00',
    modifiedAt: '2023-01-17 18:00:00',
    member: {
      id: 1,
      nickname: 'nickname',
      profileImageUrl: 'url',
    },
  },
  {
    id: 3,
    title: '제목',
    content: '시리즈',
    category: '카테고리',
    views: 100,
    thumbnailUrl: 'url~',
    createdAt: '2023-01-17 12:00:00',
    modifiedAt: '2023-01-17 18:00:00',
    member: {
      id: 1,
      nickname: 'nickname',
      profileImageUrl: 'url',
    },
  },
];

export { seriesListDummy, PostDummy, userInfoDummy, userActivitiesDummy };
