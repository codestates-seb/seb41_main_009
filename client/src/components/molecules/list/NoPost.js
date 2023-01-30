import styled from 'styled-components';
import { LabelListTitle, ParagraphMedium } from '../../../styles/typo';

const NoPost = ({ boxShadow = 'var(--boxShadow-stack)', width = '278px' }) => {
  return (
    <Container boxShadow={boxShadow}>
      <InfoLayer>
        <Title width={width}>시리즈에 아직 포스트가 없습니다.</Title>
        <Paragraph width={width}>새로운 포스트를 추가해주세요.</Paragraph>
        <ContextLayer />
      </InfoLayer>
      <ImageLayer />
    </Container>
  );
};

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
  height: 44px;

  /* Inside auto layout */
  background: #efefef;

  flex: none;
  order: 2;
  align-self: stretch;
  flex-grow: 0;
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

export default NoPost;
