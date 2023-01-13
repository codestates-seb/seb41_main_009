import styled from 'styled-components';
import { LabelListTitle, ParagraphMedium } from '../../styles/typo';

const Container = styled.div`
  box-sizing: border-box;
  width: fit-content;
  height: 180px;

  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 0px;

  border: 2px solid #333333;
  box-shadow: ${props => props.boxShadow || 'none'};
  overflow: hidden;

  /* box-shadow: ${props => props.boxShadow || 'var(--boxShadow-02) var(--gray-700)'}; */
`;

const InfoLayer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 20px 0px;

  width: fit-content;
  height: fit-content;

  /* Inside auto layout */
  background: #efefef;

  flex: none;
  order: 0;
  align-self: stretch;
  flex-grow: 1;
`;

const TextLayer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 0px 30px;

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
  padding: 5px 30px;
  gap: 10px;
  width: fit-content;
  height: fit-content;

  /* Inside auto layout */
  background: #efefef;

  flex: none;
  order: 0;
  align-self: stretch;
  flex-grow: 1;
`;

const UserBox = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 0px;
  gap: 20px;

  width: 180px;
  height: 36px;
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

const PostList = ({ boxShadow, width }) => {
  return (
    <Container boxShadow={boxShadow}>
      <InfoLayer>
        <TextLayer>
          <Title width={width}>Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.</Title>
          <Paragraph width={width}>
            Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim
            velit mollit. Exercitation veniam consequat sunt nostrud amet Amet minim mollit non deserunt ullamco est sit
            aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit. Exercitation veniam consequat
            sunt nostrud ame.
          </Paragraph>
        </TextLayer>
        <ContextLayer>
          <UserBox />
        </ContextLayer>
      </InfoLayer>
      <ImageLayer />
    </Container>
  );
};

const PostListStack = ({ boxShadow = 'var(--boxShadow-stack)', width = '278px' }) => {
  return (
    <Container boxShadow={boxShadow}>
      <InfoLayer>
        <TextLayer>
          <Title width={width}>Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.</Title>
          <Paragraph width={width}>
            Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim
            velit mollit. Exercitation veniam consequat sunt nostrud amet Amet minim mollit non deserunt ullamco est sit
            aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit. Exercitation veniam consequat
            sunt nostrud ame.
          </Paragraph>
        </TextLayer>
        <ContextLayer>
          <UserBox />
        </ContextLayer>
      </InfoLayer>
      <ImageLayer />
    </Container>
  );
};
export { PostList, PostListStack };