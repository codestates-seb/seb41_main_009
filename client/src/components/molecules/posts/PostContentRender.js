import styled from 'styled-components';
import { Icon } from '@iconify/react';
import { ParagraphMedium, HeadingLarge, HeadingSmall, HeadingMedium } from '../../../styles/typo';

const Container = styled.div`
  box-sizing: border-box;
  display: flex;
  width: calc(100% / 12 * 10);
  height: fit-content;
  gap: 10px;
  margin-bottom: 1vh;
`;

const ParagraphH1 = styled.h1`
  display: flex;
  flex-direction: row;
  padding-left: 5px;
  margin-top: 5vh;

  ${HeadingLarge}
  color: var(--gray-800);

  &:hover {
    color: var(--gray-700);
  }
`;
const ParagraphH2 = styled.h2`
  display: flex;
  flex-direction: row;
  padding-left: 5px;
  margin-top: 5vh;

  color: var(--gray-800);

  ${HeadingMedium}
  gap: 6px;

  &:hover {
    color: var(--gray-700);
  }
`;
const ParagraphH3 = styled.h3`
  display: flex;
  flex-direction: row;
  padding-left: 5px;
  margin-top: 5vh;

  color: var(--gray-800);
  gap: 6px;

  ${HeadingSmall}
  &:hover {
    color: var(--gray-700);
  }
`;

const Paragraph = styled.h3`
  display: flex;
  flex-direction: row;
  width: 100%;
  color: var(--gray-900);
  gap: 6px;

  ${ParagraphMedium}
  &:hover {
    color: var(--gray-900);
  }
`;

const PostContentRender = ({ item }) => {
  return (
    <Container>
      {item.htmlTag && item.htmlTag === 'h1' ? (
        <ParagraphH1>
          {item.content}
          {item.image ? <Icon icon="mdi:image-outline" style={{ fontSize: '20px' }} /> : null}
        </ParagraphH1>
      ) : (
        ''
      )}
      {item.htmlTag && item.htmlTag === 'h2' ? (
        <ParagraphH2>
          {item.content}
          {item.image ? <Icon icon="mdi:image-outline" style={{ fontSize: '20px' }} /> : null}
        </ParagraphH2>
      ) : (
        ''
      )}
      {item.htmlTag && item.htmlTag === 'h3' ? (
        <ParagraphH3>
          {item.content}
          {item.image ? <Icon icon="mdi:image-outline" style={{ fontSize: '20px' }} /> : null}
        </ParagraphH3>
      ) : (
        ''
      )}

      {item.htmlTag && item.htmlTag === 'p' ? (
        <Paragraph>
          {item.content}
          {item.image ? <Icon icon="mdi:image-outline" style={{ fontSize: '20px' }} /> : null}
        </Paragraph>
      ) : (
        ''
      )}
    </Container>
  );
};

export default PostContentRender;
