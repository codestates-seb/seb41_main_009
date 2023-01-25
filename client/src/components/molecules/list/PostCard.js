import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { LabelListTitle, ParagraphMedium } from '../../../styles/typo';
import { UserInfoSmall } from '../UserInfo';
import { TextButton } from '../../atoms/Buttons';
import { PARAGRAPH, TITLE } from '../../../constants/Paragraph';
import useGetPost from '../../../hooks/useGetPost';

const Container = styled.div`
  box-sizing: border-box;
  width: fit-content;
  height: fit-content;

  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 0px;

  border: 2px solid #333333;
  box-shadow: ${props => props.boxShadow || 'none'};

  /* box-shadow: ${props => props.boxShadow || 'var(--boxShadow-02) var(--gray-700)'}; */
`;

const InfoLayer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 18px 20px;
  width: fit-content;
  height: fit-content;
  & > .link {
    text-decoration: none;
    color: var(--gray-800);
    &:hover {
      color: var(--gray-500);
    }
  }

  /* Inside auto layout */
  background: #efefef;

  flex: none;
  order: 0;
  align-self: stretch;
  flex-grow: 1;
`;

const ContextLayer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 5px 0px;
  gap: 10px;
  width: fit-content;
  height: fit-content;

  /* Inside auto layout */
  background: #efefef;

  flex: none;
  order: 2;
  align-self: stretch;
  flex-grow: 0;
`;

const UserBox = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 10px;
  gap: 20px;

  width: fit-content;
  height: fit-content;
`;
const Title = styled.div`
  width: ${props => props.width || '534px'};
  height: 48px;
  overflow: hidden;
  text-overflow: ellipsis;
  text-decoration: none;

  ${LabelListTitle}
`;
const Paragraph = styled.div`
  width: ${props => props.width || '534px'};
  height: 42px;
  overflow: hidden;
  text-overflow: ellipsis;
  text-decoration: none;

  ${ParagraphMedium}
`;

const ImageLayer = styled.img`
  width: 280px;
  height: 180px;

  background: var(--gray-800);
  display: block;
  object-fit: cover;
`;

/**
 * 쇼케이스에서 사용하는 이미지 썸네일 molecules
 * @param {string|number} boxShadow - 전체컨테이너의 그림자 효과
 * @param {string} width - text의 길이
 * @returns {JSX.Element} - PostList 개별 항목을 나타내는 컴포넌트
 */
const PostCard = ({ boxShadow, width, postId }) => {
  // 현재는postId와 관계없이 PostDummy에 있는 데이터를 가져옴
  const { post, isLoading, isLoadingError } = useGetPost(postId);

  // isLoading, isLoadingError state에 따라 컴포넌트 변경 예정
  console.log(isLoading, isLoadingError);
  return (
    <Container boxShadow={boxShadow}>
      <InfoLayer>
        <Link className="link" to={`/posts/category/${postId}`}>
          <Title width={width}>{post.title || TITLE}</Title>
          <Paragraph width={width}>{post.Paragraph || PARAGRAPH}</Paragraph>
        </Link>
        <ContextLayer>
          <UserBox>
            <UserInfoSmall name="UserName" image="https://unsplash.it/1920/1080/?random" />
          </UserBox>
          <TextButton width="30px"> text</TextButton>
          <TextButton width="30px"> text</TextButton>
        </ContextLayer>
      </InfoLayer>
      <ImageLayer />
    </Container>
  );
};

/**
 * 쇼케이스에서 사용하는 이미지 썸네일 molecules
 * @param {string|number} boxShadow - 전체컨테이너의 그림자 효과
 * @param {string} width - text의 길이
 * @returns {JSX.Element} - PostListStack을 나타내는 컴포넌트
 */
const PostListStack = ({ boxShadow = 'var(--boxShadow-stack)', width = '278px', postId }) => {
  const { post, isLoading, isLoadingError } = useGetPost(postId);

  // isLoading, isLoadingError state에 따라 컴포넌트 변경 예정
  console.log(isLoading, isLoadingError);

  return (
    <Container boxShadow={boxShadow}>
      <InfoLayer>
        <Title width={width}>
          {post.title || 'Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.'}{' '}
        </Title>
        <Paragraph width={width}>
          {post.Paragraph ||
            'Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim            velit mollit. Exercitation veniam consequat sunt nostrud amet Amet minim mollit non deserunt ullamco est sitaliqua dolor do amet sint. Velit officia consequat duis enim velit mollit. Exercitation veniam consequatsunt nostrud ame.'}
        </Paragraph>
        <ContextLayer>
          <UserBox>
            <UserInfoSmall name="UserName" image="https://unsplash.it/1920/1080/?random" />
          </UserBox>
          <TextButton width="30px"> text</TextButton>
          <TextButton width="30px"> text</TextButton>
        </ContextLayer>
      </InfoLayer>
      <ImageLayer />
    </Container>
  );
};
export { PostCard, PostListStack };
