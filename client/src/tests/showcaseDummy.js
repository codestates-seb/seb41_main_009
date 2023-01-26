export const TEST_SHOWCASE_LIST_RES = {
  id: 1,
  content:
    '푸른 언덕에 배낭을 메고 황금빛 태양 축제를 여는 광야를 향해서 계곡을 향해서 먼 동이 트는 이른 아침에 도시의 소음 수 많은 사람 빌딩 숲 속을 벗어나봐요',
  category: '여행',
  views: 100,
  comments: 100,
  isItWriter: false,
  thumbnailUrl:
    'https://images.pexels.com/photos/3558637/pexels-photo-3558637.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
  createdAt: '2023-01-08 12:00:00',
  modifiedAt: '2023-01-08 18:00:00',
  writer: {
    // 작성자 정보
    id: 1, // 쇼케이스 작성자 식별자
    nickname: 'Hojung', // 쇼케이스 작성자 닉네임
    profileImageUrl:
      'https://images.pexels.com/photos/3558637/pexels-photo-3558637.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', // 쇼케이스 작성자 프로필 사진
    isSubscribed: false, // 작성자에 대한 구독여부
  },
};

export const TEST_SHOWCASE_MODAL_RES = {
  id: 1,
  content:
    '푸른 언덕에 배낭을 메고 황금빛 태양 축제를 여는 광야를 향해서 계곡을 향해서 먼 동이 트는 이른 아침에 도시의 소음 수 많은 사람 빌딩 숲 속을 벗어나봐요', // 내용
  category: 'Category', // 카테고리
  views: 100, // 조회수
  isItWriter: false, // 작성자 여부
  createdAt: '2023-01-08 12:00:00', // 작성일
  modifiedAt: '2023-01-08 18:00:00', // 수정일
  images: [
    // 이미지 url
    {
      index: 1,
      fileURL:
        'https://images.pexels.com/photos/3558637/pexels-photo-3558637.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
    },
    {
      index: 2,
      fileURL:
        'https://images.pexels.com/photos/3558637/pexels-photo-3558637.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
    },
  ],
  writer: {
    // 작성자 정보
    id: 1, // 쇼케이스 작성자 식별자
    nickname: 'nickname', // 쇼케이스 작성자 닉네임
    profileImageUrl:
      'https://images.pexels.com/photos/3558637/pexels-photo-3558637.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', // 쇼케이스 작성자 프로필 사진
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
        profileImageUrl:
          'https://images.pexels.com/photos/3558637/pexels-photo-3558637.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', // 댓글 작성자 프로필 사진
      },
    },
  ],
};
