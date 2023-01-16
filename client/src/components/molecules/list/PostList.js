import styled from 'styled-components';
import { useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import axios from 'axios';
import { LabelListTitle, ParagraphMedium } from '../../../styles/typo';
import { UserInfoSmall } from '../UserInfo';
import { TextButton } from '../../atoms/Buttons';
import { PARAGRAPH, TITLE } from '../../../constants/Paragraph';

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
  ${LabelListTitle}
`;
const Paragraph = styled.div`
  width: ${props => props.width || '534px'};
  height: 42px;
  overflow: hidden;
  text-overflow: ellipsis;

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
const PostList = ({ boxShadow, width }) => {
  const [post, setPost] = useState({});
  const { postId } = useParams();

  useEffect(() => {
    const getData = async () => {
      await axios(`URL/${postId}`)
        .then(res => setPost(res.data.data))
        .catch(error => console.log(error));
    };

    getData();
  }, [postId]);
  return (
    <Container boxShadow={boxShadow}>
      <InfoLayer>
        <Title width={width}>{post.title || TITLE}</Title>
        <Paragraph width={width}>{post.Paragraph || PARAGRAPH}</Paragraph>
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
const PostListStack = ({ boxShadow = 'var(--boxShadow-stack)', width = '278px' }) => {
  const [post, setPost] = useState({});
  const { postId } = useParams();

  useEffect(() => {
    const getData = async () => {
      await axios(`http://3.37.105.24:8080/questions/${postId}`)
        .then(res => setPost(res.data.data))
        .catch(error => console.log(error));
    };

    getData();
  }, [postId]);
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
export { PostList, PostListStack };
