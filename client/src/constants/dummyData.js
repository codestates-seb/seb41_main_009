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

// 특정한 시리즈의 정보
const SeriesDummy = {
  data: {
    id: 1, // series의 id
    title: '제목', // series의 제목
    content: '내용', // series의 내용
    category: '뜨개질', // series의 카테고리
    views: 0, // 시리즈 조회수
    thumbnailUrl: 'http://domain.com/bucket/series/file.png', // 시리즈 썸네일 사진
    member: {
      // 시리즈 작성자 정보
      id: 1, // 작성자 id
      nickname: 'nickname', // 작성자 nickname
      profileUrl: 'http://domain.com/bucket/members/file3.png', // 작성자 프로필 사진
    },
    createdAt: '2023-01-19T17:39:03.049077', // series 생성 일자
    modifiedAt: '2023-01-19T17:42:54.280894', // 마지막 수정 일자
  },
};

// 특정한 시리즈에 포함되어 있는 Post의 목록s
const SeriesPostLIstDummy = {
  data: [
    {
      id: 1, // 포스트 식별자
      title: 'title', // 제목
      content: 'content', // 내용
      category: 'category', // 카테고리
      views: 100, // 조회수
      comments: 100, // 댓글수
      seriesId: 1, // 시리즈Id
      thumbnailUrl: 'thumbnailUrl', // 대표 이미지
      createdAt: '2023-01-10 12:00:00', // 작성일
      modifiedAt: '2023-01-10 18:00:00', // 수정일
      writer: {
        // 작성자 정보
        id: 1, // 포스트 작성자 식별자
        nickname: 'nickname', // 포스트 작성자 닉네임
        profileImageUrl: 'url', // 포스트 작성자 프로필 사진
      },
    },
    {
      id: 1, // 포스트 식별자
      title: 'title', // 제목
      content: 'content', // 내용
      category: 'category', // 카테고리
      views: 100, // 조회수
      comments: 100, // 댓글수
      seriesId: 1, // 시리즈Id
      thumbnailUrl: 'thumbnailUrl', // 대표 이미지
      createdAt: '2023-01-10 12:00:00', // 작성일
      modifiedAt: '2023-01-10 18:00:00', // 수정일
      writer: {
        // 작성자 정보
        id: 1, // 포스트 작성자 식별자
        nickname: 'nickname', // 포스트 작성자 닉네임
        profileImageUrl: 'url', // 포스트 작성자 프로필 사진
      },
    },
    {
      id: 2, // 포스트 식별자
      title: 'title', // 제목
      content: 'content', // 내용
      category: 'category', // 카테고리
      views: 100, // 조회수
      comments: 100, // 댓글수
      seriesId: 1, // 시리즈Id
      thumbnailUrl: 'thumbnailUrl', // 대표 이미지
      createdAt: '2023-01-10 12:00:00', // 작성일
      modifiedAt: '2023-01-10 18:00:00', // 수정일
      writer: {
        // 작성자 정보
        id: 1, // 포스트 작성자 식별자
        nickname: 'nickname', // 포스트 작성자 닉네임
        profileImageUrl: 'url', // 포스트 작성자 프로필 사진
      },
    },
    {
      id: 3, // 포스트 식별자
      title: 'title', // 제목
      content: 'content', // 내용
      category: 'category', // 카테고리
      views: 100, // 조회수
      comments: 100, // 댓글수
      seriesId: 1, // 시리즈Id
      thumbnailUrl: 'thumbnailUrl', // 대표 이미지
      createdAt: '2023-01-10 12:00:00', // 작성일
      modifiedAt: '2023-01-10 18:00:00', // 수정일
      writer: {
        // 작성자 정보
        id: 1, // 포스트 작성자 식별자
        nickname: 'nickname', // 포스트 작성자 닉네임
        profileImageUrl: 'url', // 포스트 작성자 프로필 사진
      },
    },
  ],
  pageInfo: {
    page: '1', // 페이지 번호
    size: '10', // 포스트 수
    totalPage: '10', // 존재하는 총 페이지 수
    totalElements: '100', // 존재하는 총 포스트 수
  },
};

const postListDummy = {
  data: [
    {
      id: 1, // 포스트 식별자
      title: 'title', // 제목
      content: 'content', // 내용
      category: 'category', // 카테고리
      views: 100, // 조회수
      comments: 100, // 댓글수
      seriesId: 1, // 시리즈Id
      isItWriter: false, // 작성자 여부
      thumbnailUrl: 'thumbnailUrl', // 대표 이미지
      createdAt: '2023-01-10 12:00:00', // 작성일
      modifiedAt: '2023-01-10 18:00:00', // 수정일
      writer: {
        // 작성자 정보
        id: 1, // 포스트 작성자 식별자
        nickname: 'nickname', // 포스트 작성자 닉네임
        profileImageUrl: 'url', // 포스트 작성자 프로필 사진
      },
    },
    {
      id: 2, // 포스트 식별자
      title: 'title', // 제목
      content: 'content', // 내용
      category: 'category', // 카테고리
      views: 100, // 조회수
      comments: 100, // 댓글수
      seriesId: 1, // 시리즈Id
      isItWriter: false, // 작성자 여부
      thumbnailUrl: 'thumbnailUrl', // 대표 이미지
      createdAt: '2023-01-10 12:00:00', // 작성일
      modifiedAt: '2023-01-10 18:00:00', // 수정일
      writer: {
        // 작성자 정보
        id: 1, // 포스트 작성자 식별자
        nickname: 'nickname', // 포스트 작성자 닉네임
        profileImageUrl: 'url', // 포스트 작성자 프로필 사진
      },
    },
    {
      id: 3, // 포스트 식별자
      title: 'title', // 제목
      content: 'content', // 내용
      category: 'category', // 카테고리
      views: 100, // 조회수
      comments: 100, // 댓글수
      seriesId: 1, // 시리즈Id
      isItWriter: false, // 작성자 여부
      thumbnailUrl: 'thumbnailUrl', // 대표 이미지
      createdAt: '2023-01-10 12:00:00', // 작성일
      modifiedAt: '2023-01-10 18:00:00', // 수정일
      writer: {
        // 작성자 정보
        id: 1, // 포스트 작성자 식별자
        nickname: 'nickname', // 포스트 작성자 닉네임
        profileImageUrl: 'url', // 포스트 작성자 프로필 사진
      },
    },
  ],
  pageInfo: {
    page: '1', // 페이지 번호
    size: '10', // 포스트 수
    totalPage: '10', // 존재하는 총 페이지 수
    totalElements: '100', // 존재하는 총 포스트 수
  },
};
const PostDummy = {
  data: {
    id: 1, // 포스트 식별자
    title: 'title', // 제목
    content:
      '<h1> h1제목입니다</h1><p> 사용자가 코치의 도움없이 여러 훈련을 개인적으로 할 수 있도록 도와주는 어플리케이션입니다. </p> <h2> h2제목입니다</h2> <p> 사용자가 코치의 도움없이 여러 훈련을 개인적으로 할 수 있도록 도와주는 어플리케이션입니다. </p> <h3> h3제목입니다</h3><p> 사용자가 코치의 도움없이 여러 훈련을 개인적으로 할 수 있도록 도와주는 어플리케이션입니다. </p>',
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

export {
  seriesListDummy,
  SeriesDummy,
  SeriesPostLIstDummy,
  postListDummy,
  PostDummy,
  userInfoDummy,
  userActivitiesDummy,
};
